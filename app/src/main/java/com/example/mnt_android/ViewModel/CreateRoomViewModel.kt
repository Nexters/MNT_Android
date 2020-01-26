package com.example.mnt_android.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mnt_android.service.model.RoomInfo
import com.example.mnt_android.view.ui.CreateRoomActivity
import com.kakao.kakaolink.v2.KakaoLinkResponse
import com.kakao.kakaolink.v2.KakaoLinkService
import com.kakao.message.template.LinkObject
import com.kakao.message.template.TextTemplate
import com.kakao.network.ErrorResult
import com.kakao.network.callback.ResponseCallback
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







}