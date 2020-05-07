package com.nexters.frutto.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nexters.frutto.base.BaseViewModel
import com.nexters.frutto.extension.checkUploadDate
import com.nexters.frutto.service.model.UserMissionResponse
import com.nexters.frutto.service.repository.PreferencesRepository

class MissionDetailViewModel(private val pr: PreferencesRepository) : BaseViewModel() {
    private val _mission = MutableLiveData<UserMissionResponse>()
    val mission: LiveData<UserMissionResponse> = _mission

    val naetoId: LiveData<Int>
        get() = MutableLiveData(mission.value?.userFruttoId)
    val nitoId: LiveData<Int>
        get() = MutableLiveData(mission.value?.manitto?.fruttoId)
    val naetoName: LiveData<String>
        get() = MutableLiveData(
            when (getIsManager() || getCheckNaeto()) {
                true -> mission.value?.userMission?.user?.name
                else -> mission.value?.userFruttoId.toString()
            }
        )
    val nitoName: LiveData<String>
        get() = MutableLiveData(mission.value?.manitto?.name)
    val missionImg: LiveData<String>
        get() = MutableLiveData(mission.value?.userMission?.missionImg)
    val missionName: LiveData<String>
        get() = MutableLiveData(mission.value?.missionName)
    val missionContent: LiveData<String>
        get() = MutableLiveData(mission.value?.userMission?.content)
    val updateDate: LiveData<String>
        get() = MutableLiveData(mission.value?.userMission?.userDoneTime?.checkUploadDate)

    fun setMission(missionObj: UserMissionResponse) {
        _mission.value = missionObj
    }

    fun getIsManager() = pr.getIsManager()
    fun getCheckNaeto() = pr.getCheckNaeto()
}