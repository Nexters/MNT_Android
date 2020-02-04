package com.example.mnt_android.service.model

import androidx.lifecycle.MutableLiveData

class CreateMission
{
    var missionName : MutableLiveData<String> = MutableLiveData()
    var missionDescription : MutableLiveData<String> = MutableLiveData()
    var imageCheck : MutableLiveData<Boolean> = MutableLiveData()

    constructor(missionname : String, missiondescription : String, imagecheck : Boolean)
    {
        missionName.value=missionname
        missionDescription.value = missiondescription
        imageCheck.value=imagecheck
    }


    fun createMission()
    {

    }

}