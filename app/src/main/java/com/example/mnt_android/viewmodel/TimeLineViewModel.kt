package com.example.mnt_android.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mnt_android.base.BaseViewModel
import com.example.mnt_android.bus.*
import com.example.mnt_android.extension.isFalse
import com.example.mnt_android.service.model.Applicant
import com.example.mnt_android.service.model.UserMissionResponse
import com.example.mnt_android.service.repository.DBRepository
import com.example.mnt_android.service.repository.PreferencesRepository
import com.example.mnt_android.util.SUCCESS
import com.example.mnt_android.vo.MissionVO
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class TimeLineViewModel(
    private val dbRepository: DBRepository,
    private val pr: PreferencesRepository
) : BaseViewModel() {
    private var filterType = arrayOf(MISSION_LIST_ALL)
    private val _allContentList = MutableLiveData<ArrayList<UserMissionResponse>>()
    private val _contentList = MutableLiveData<ArrayList<UserMissionResponse>>()
    val contentList: LiveData<ArrayList<UserMissionResponse>> = _contentList

    fun setMissionList(success: (ArrayList<MissionVO>) -> Unit) {
        addDisposable(
            dbRepository.groupByMission(pr.getRoomId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    it.run {
                        val missionList: ArrayList<MissionVO> = arrayListOf()
                        missionResponses.forEach { mission ->
                            missionList.add(MissionVO(mission.id, mission.missionName))
                        }
                        success(missionList)
                    }
                }, {})
        )
    }

    fun setUserList(success: (ArrayList<Applicant>) -> Unit) {
        addDisposable(
            dbRepository.userList(pr.getRoomId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    it.run {
                        if (apiStatus.httpStatus == SUCCESS) {
                            val userList = arrayListOf<Applicant>()
                            data.forEach { applicant ->
                                if (applicant.isCreater.isFalse) userList.add(applicant)
                            }
                            success(userList)
                        }
                    }
                }, {})
        )
    }

    fun setContentList(success: () -> Unit) {
        addDisposable(
            dbRepository.getMissionList(pr.getRoomId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ ml ->
                    ml.run {
                        if (apiStatus.httpStatus == SUCCESS) {
                            _allContentList.value =
                                ArrayList(data.sortedWith(compareByDescending { it.userMission.userDoneTime }))
                            success()
                        }
                    }
                }, {})
        )
    }

    fun setFilteredContentList(filterContents: Array<String>) {
        setContentList {
            filterType = filterContents
            var filteredList = arrayListOf<UserMissionResponse>()
            when (filterType[0]) {
                MISSION_LIST_ALL -> _allContentList.value?.let { filteredList = it }
                MISSION_LIST_TO_ME, MISSION_LIST_PARTICIPANT -> {
                    _allContentList.value?.forEach {
                        if (it.manitto?.id == filterContents[1]) filteredList.add(it)
                    }
                }

                MISSION_LIST_FROM_ME -> {
                    _allContentList.value?.forEach {
                        if (it.userMission.user.id == filterContents[1]) filteredList.add(it)
                    }
                }

                MISSION_LIST_MISSION_TYPE -> {
                    _allContentList.value?.forEach {
                        if (it.missionId == filterContents[1].toInt()) filteredList.add(it)
                    }
                }
            }
            _contentList.value = filteredList
        }
    }

    fun setFilteredContentList() {
        setFilteredContentList(filterType)
    }

    fun getUserId(): String = pr.getUserId()
    fun getUserNm(): String = pr.getUserNm()
    fun getUserFruttoId() = pr.getFruttoId()
    fun getIsManager() = pr.getIsManager()
}