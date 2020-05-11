package com.nexters.frutto.viewmodel

import android.icu.text.SimpleDateFormat
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.nexters.frutto.base.BaseViewModel
import com.nexters.frutto.service.model.Room
import com.nexters.frutto.service.model.RoomId
import com.nexters.frutto.service.model.SendRoom
import com.nexters.frutto.service.repository.DBRepository
import com.nexters.frutto.view.ui.MainActivity
import io.reactivex.android.schedulers.AndroidSchedulers
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
    var isStarted : MutableLiveData<Int> =  MutableLiveData()
  lateinit var roomId : RoomId
 var userId : String = ""
 lateinit var format : SimpleDateFormat

  init {
      room.value = Room("",0,0,0,1,name,"")

      roomId = RoomId(0)
     format = SimpleDateFormat("yyyy-MM-dd")




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
    repository.startRoom(id)
     .subscribeOn(Schedulers.io())
     .observeOn(AndroidSchedulers.mainThread())
        .subscribe({
            if(it.apiStatus.label=="방 인원이 충분하지 않습니다")
                isStarted.value = 2
            else
                isStarted.value = 1
        },{

        })
   )
 }

    fun deleteRoom() {
        addDisposable(
            repository.deleteRoom(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({

                 },{

                })
        )
    }

}