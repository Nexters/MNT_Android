package com.example.mnt_android.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mnt_android.base.BaseViewModel
import com.example.mnt_android.extension.checkUploadDate
import com.example.mnt_android.service.model.UserMissionResponse

class MissionDetailViewModel : BaseViewModel() {
    var isManager = MutableLiveData<Boolean>(false)

    private val _mission = MutableLiveData<UserMissionResponse>()
    val mission: LiveData<UserMissionResponse> = _mission

    val naetoId: LiveData<Int>
        get() = MutableLiveData(mission.value?.userFruttoId)
    val nitoId: LiveData<Int>
        get() = MutableLiveData(mission.value?.manitto?.fruttoId)
    val naetoName: LiveData<String>
        get() = MutableLiveData(
            when (isManager.value) {
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
}