package com.example.mnt_android.viewmodel

import android.icu.text.SimpleDateFormat
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.mnt_android.base.BaseViewModel
import com.example.mnt_android.service.model.Room
import com.example.mnt_android.service.model.RoomId
import com.example.mnt_android.service.model.SendRoom
import com.example.mnt_android.service.repository.DBRepository
import com.example.mnt_android.view.ui.MainActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Action
import io.reactivex.schedulers.Schedulers

class CreateRoomViewModel : BaseViewModel()
{


 var room : MutableLiveData<Room> = MutableLiveData()
  var fragmentNum = 0
  private val repository = DBRepository()
  var name : String=""
  var startDay : MutableLiveData<String> = MutableLiveData()
    var endDay : MutableLiveData<String> = MutableLiveData()
  var maxPeople : String = ""
 var id : Long = -1
 var isCreated : MutableLiveData<Boolean> = MutableLiveData()
    var isStarted : MutableLiveData<Boolean> =  MutableLiveData()
  lateinit var roomId : RoomId
 var userId : String = ""
 lateinit var format : SimpleDateFormat

  init {
      room.value = Room("",0,0,0,1,name,"")

      roomId = RoomId(0)
     format = SimpleDateFormat("yyyy-MM-dd")




  }

  fun setDate()
  {

  }

  fun setRoomInfo()
  {



  }


  fun createRoom()
  {

   room.value = Room( endDay.value!!,0,0,0,maxPeople.toInt(),name,startDay.value!!)
   addDisposable(
    repository.createRoom(SendRoom(format.parse(room.value!!.endDay),0,0,0,maxPeople.toInt(),name,format.parse(room.value!!.startDay)),MainActivity.userId)
     .subscribeOn(Schedulers.io())
     .observeOn(AndroidSchedulers.mainThread())
     .subscribe({ t: RoomId? ->

      roomId.roomId=t!!.roomId
      id = roomId.roomId

      Log.d("wlgusdnzzz",t?.roomId.toString())

      isCreated.value=true

     })
   )
   //API로 방 생성하기
   // repository.createRoom()
  }

 fun startRoom() {
   addDisposable(
    repository.startRoom(id.toLong())
     .subscribeOn(Schedulers.io())
     .observeOn(AndroidSchedulers.mainThread())
        .subscribe(Action {
            //방이 정상적으로 시작되었는 지 확인 필요 (인원 문제)
            isStarted.value=true
            Log.d("wlgusdnzzz","방시작됨")
        })
   )
 }

}