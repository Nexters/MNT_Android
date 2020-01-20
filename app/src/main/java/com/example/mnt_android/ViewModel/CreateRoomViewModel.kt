package com.example.mnt_android.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mnt_android.service.model.RoomInfo
import kotlin.random.Random

class CreateRoomViewModel : ViewModel()
{

  var roomInfo : RoomInfo = RoomInfo()
  var fragmentNum = 0

  fun setRoomInfo()
  {
    roomInfo.num.value = Random(100000).nextInt()
    Log.d("wlgusdnzz",roomInfo.num.value.toString())

  }




}