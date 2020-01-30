package com.example.mnt_android.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.mnt_android.service.model.RoomInfo
import kotlin.random.Random

class CreateRoomViewModel : ViewModel()
{

  var roomInfo : RoomInfo = RoomInfo()
  var fragmentNum = 0

  fun setRoomInfo()
  {
    roomInfo.num.value = Random(100000).nextInt().toString()//이후에 API로 방번호 설정
    Log.d("wlgusdnzz",roomInfo.num.value.toString())

  }

  fun findParticipants()
  {
    //api로 참가자들 정보 받아오기
  }






}