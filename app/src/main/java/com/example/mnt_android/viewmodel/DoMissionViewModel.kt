package com.example.mnt_android.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mnt_android.service.model.DoMission

class DoMissionViewModel : ViewModel()
{

    var doMission : MutableLiveData<DoMission> = MutableLiveData()
    var fragmentNum = 0
    var str : String?=null
    init {
        //넘겨받은 값으로 초기화
        doMission.value?.name="미션이름"
        doMission.value?.description="미션설명"
        doMission.value?.textMission=""
        doMission.value?.imgMission=null
    }

    fun setMission()
    {
        doMission.value?.textMission=str!!
    }



}