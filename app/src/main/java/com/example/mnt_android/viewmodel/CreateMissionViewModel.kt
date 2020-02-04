package com.example.mnt_android.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mnt_android.service.model.CreateMission
import com.kakao.auth.KakaoSDK.init

class CreateMissionViewModel : ViewModel()
{
    var createMission : CreateMission

    var direct : MutableLiveData<Boolean> = MutableLiveData()
    var name : MutableLiveData<String> = MutableLiveData()
    var des : MutableLiveData<String> = MutableLiveData()
    var send : MutableLiveData<Boolean> = MutableLiveData()

    init {
        direct.value=false
        createMission = CreateMission("미션이름","미션설명",false)
    }



    fun setMission(i : Int)
    {

        when(i)
        {
            0->{
               name.value="미션이름0"
                des.value="미션설명0"


            }
            1->{
                name.value="미션이름1"
               des.value="미션설명1"
            }
            2->{
                name.value="미션이름2"
                des.value="미션설명2"
            }
            3->{
                name.value="미션이름3"
                des.value="미션설명3"
            }
            4->{
                name.value=null
                des.value=null
            }
        }

    }

    fun writeDirect(i : Int)
    {
        if(i==4)
            direct.value=true
        else
            direct.value=false
    }

    fun sendMission()
    {

        createMission.missionName.value=name.value
        createMission.missionDescription.value=des.value
        send.value=true
        //API 호출
    }

    fun checkImage()
    {
        when(createMission.imageCheck.value)
        {
            false->
            {
                createMission.imageCheck.value=true
            }
            true->
            {
                createMission.imageCheck.value=false
            }
        }
    }



}