package com.nexters.frutto.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nexters.frutto.base.BaseViewModel
import com.nexters.frutto.bus.*
import com.nexters.frutto.extension.isFalse
import com.nexters.frutto.service.model.Applicant
import com.nexters.frutto.service.model.UserMissionResponse
import com.nexters.frutto.service.repository.DBRepository
import com.nexters.frutto.service.repository.PreferencesRepository
import com.nexters.frutto.util.SUCCESS
import com.nexters.frutto.vo.MissionVO
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
            dbRepository.getUserMission(pr.getUserId(), pr.getRoomId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    it.run {
                        val missionList: ArrayList<MissionVO> = arrayListOf()
                        data.forEach { mission ->
                            missionList.add(MissionVO(mission.missionId, mission.missionName))
                        }
                        success(missionList)
                    }
                }, {})
        )
    }

    fun setUserList(success: (ArrayList<Applicant>) -> Unit) {
        if(getUserList() == null) {
            addDisposable(
                dbRepository.userList(pr.getRoomId())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        it.run {
                            if (apiStatus.httpStatus == SUCCESS) {
                                pr.setUserList(data)
                                val userList = arrayListOf<Applicant>()
                                data.forEach { applicant ->
                                    if (applicant.isCreater.isFalse) userList.add(applicant)
                                }
                                success(userList)
                            }
                        }
                    }, {})
            )
        } else {
            success(pr.getUserList() ?: arrayListOf())
        }
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
    fun getCheckNaeto() = pr.getCheckNaeto()
    fun getUserList() = pr.getUserList()
}