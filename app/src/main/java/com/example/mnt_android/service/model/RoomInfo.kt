package com.example.mnt_android.service.model

import androidx.lifecycle.MutableLiveData

class RoomInfo
{
    var num : MutableLiveData<String> = MutableLiveData()
    var name : MutableLiveData<String> = MutableLiveData()
    var maxPersonNum : MutableLiveData<String> = MutableLiveData()
    var startDate : MutableLiveData<String> = MutableLiveData()
    var endDate : MutableLiveData<String> = MutableLiveData()
    var description : MutableLiveData<String> = MutableLiveData()
    var isStarted : Boolean = false



}