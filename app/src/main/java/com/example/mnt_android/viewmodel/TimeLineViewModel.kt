package com.example.mnt_android.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mnt_android.base.BaseViewModel
import com.example.mnt_android.bus.*
import com.example.mnt_android.service.model.UserMissionResponse
import com.example.mnt_android.service.repository.DBRepository
import com.example.mnt_android.util.SUCCESS
import com.example.mnt_android.vo.MissionVO
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class TimeLineViewModel(private val dbRepository: DBRepository) : BaseViewModel(){
    val isManager = MutableLiveData<Boolean>(false)
    private var filterType = arrayOf(MISSION_LIST_ALL)
    private val _allContentList = MutableLiveData<ArrayList<UserMissionResponse>>()
    private val _contentList = MutableLiveData<ArrayList<UserMissionResponse>>()
    val contentList: LiveData<ArrayList<UserMissionResponse>> = _contentList
    var missionList: ArrayList<MissionVO> = arrayListOf()

    fun setMissionList(userId: String, roomId: Long, success: () -> Unit) {
        addDisposable(
            dbRepository.getUserMission(userId, roomId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    it.run {
                        if (apiStatus.httpStatus == SUCCESS) {
                            missionList = arrayListOf()
                            data.forEach { mission ->
                                missionList.add(MissionVO(mission.missionId, mission.missionName))
                            }
                            success()
                        }
                    }
                }, {})
        )
    }

    fun setContentList(roomId: Long, success: () -> Unit) {
        addDisposable(
            dbRepository.getMissionList(roomId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    it.run {
                        if(apiStatus.httpStatus == SUCCESS) {
                            _allContentList.value = data
                            success()
                        }
                    }
                }, {})
        )
    }

    fun setFilteredContentList(roomId: Long, filterContents: Array<String>) {
        setContentList(roomId){
            filterType = filterContents
            var filteredList = arrayListOf<UserMissionResponse>()
            when(filterType[0]) {
                MISSION_LIST_ALL -> _allContentList.value?.let { filteredList = it }
                MISSION_LIST_TO_ME, MISSION_LIST_PARTICIPANT -> {
                    _allContentList.value?.forEach {
                        if(it.manitto?.id == filterContents[1]) filteredList.add(it)
                    }
                }

                MISSION_LIST_FROM_ME -> {
                    _allContentList.value?.forEach {
                        if(it.userMission.user.id == filterContents[1]) filteredList.add(it)
                    }
                }

                MISSION_LIST_MISSION_TYPE -> {
                    _allContentList.value?.forEach {
                        if(it.missionId == filterContents[1].toLong()) filteredList.add(it)
                    }
                }
            }
            _contentList.value = filteredList
        }
    }

    fun setFilteredContentList(roomId: Long) {
        setFilteredContentList(roomId, filterType)
    }
}