package com.example.mnt_android.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mnt_android.service.model.KakaoUser
import com.example.mnt_android.service.model.RoomInfo
import kotlin.random.Random

class JoinRoomViewModel : ViewModel()
{

    var roomInfo : RoomInfo = RoomInfo()
    var fragmentNum = 0
    var str : String = "준비하기"


    fun findRoom(roomNum : String)
    {

        //서버에서 방 정보 찾기

        roomInfo.num.value=roomNum
        roomInfo.name.value="방이름123"
        roomInfo.startDate.value="시작날짜123"
        roomInfo.endDate.value="종료날짜123"
        roomInfo.description.value = "방 설명123"


    }
    fun ready()
    {
       if(roomInfo.ready==false)
       {
           roomInfo.ready=true
           str = "준비완료"
       }
        else
       {
           roomInfo.ready=false
           str="준비하기"
       }
    }

}