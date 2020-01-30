package com.example.mnt_android.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mnt_android.service.model.KakaoUser
import com.example.mnt_android.service.model.RoomInfo
import com.example.mnt_android.view.ui.LoginActivity
import kotlin.random.Random

class JoinRoomViewModel(application: Application) : AndroidViewModel(application)
{
    val app = application
    var roomInfo : RoomInfo = RoomInfo()
   var checkNitto : MutableLiveData<Boolean> = MutableLiveData()
    var fragmentNum = 0

    init {
        checkNitto.value=false
    }

    fun findRoom(roomNum : String)
    {

        //서버에서 방 정보 찾기

        roomInfo.num.value=roomNum
        roomInfo.name.value="방이름123"
        roomInfo.startDate.value="시작날짜123"
        roomInfo.endDate.value="종료날짜123"
        roomInfo.description.value = "방 설명123"


    }



    fun checkNitto()
    {

       val sf=app.getSharedPreferences("login",0)
        sf.edit().putBoolean("checkNitto",true)
        checkNitto.value=true

    }

    fun findParticipants()
    {
        //api로 참가자들 정보 받아오기
    }

}