package com.example.mnt_android.viewmodel

import android.app.Application
import android.graphics.Color
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.View
import androidx.databinding.ObservableInt
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.mnt_android.service.model.CheckRoom
import com.example.mnt_android.service.model.CheckRoomList
import com.example.mnt_android.service.model.RoomInfo
import com.example.mnt_android.service.repository.DBRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

class JoinRoomViewModel(application: Application) : AndroidViewModel(application)
{
    val app = application
    var roomInfo : RoomInfo = RoomInfo()
    var sf = app.getSharedPreferences("login",0)
   var checkNitto : MutableLiveData<Boolean> = MutableLiveData()
    var fragmentNum = 0
    var checkRoom  : MutableLiveData<CheckRoom> = MutableLiveData()
    private val repository = DBRepository()
    var isJoined : MutableLiveData<Boolean> = MutableLiveData()
    var isSearched : MutableLiveData<Boolean> = MutableLiveData()
    var isStarted : MutableLiveData<Boolean> = MutableLiveData()
    var isLogined : MutableLiveData<Boolean> = MutableLiveData()
    var isManager : MutableLiveData<Boolean> = MutableLiveData()
    var progressBar : ObservableInt
    var startDayText_joinroom2 : String=""
    var myName = ""
    var nittoName="ㅁㅁㅁ"
    var name_joinroom3 = SpannableStringBuilder()
    var startDayText_joinroom3 : String = ""
    init {
        checkNitto.value=false
        //isJoined.value=false
        //isSearched.value=false
        myName = sf.getString("kakao_nickname","")

        //임시
        name_joinroom3 = SpannableStringBuilder("$myName 의 마니또는 $nittoName 입니다.")

        name_joinroom3.setSpan(ForegroundColorSpan(Color.parseColor("#ff5050")),
            0,myName.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        name_joinroom3.setSpan(ForegroundColorSpan(Color.parseColor("#ff5050")),
            myName.length+7,myName.length+7+nittoName.length-1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        isStarted.value=false
        progressBar = ObservableInt(View.GONE)
    }

    fun checkRoom()
    {
        //참가한 방이 존재하는지 확인
        progressBar.set(View.VISIBLE)
        if(sf.getString("kakao_token","null")!="null")
        {
            repository.checkRoom(sf.getString("kakao_token", "null"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ t: CheckRoomList? ->
                    if (t?.checkRoomList != null) {
                        checkRoom.value = t!!.checkRoomList[0]
                        startDayText_joinroom2  = "${checkRoom.value!!.room.startDay} 에 시작합니다.\n친구들이 모일때까지 잠시 기다려주세요"

                        Log.d("wlgusdnzzz",checkRoom.value!!.room.isStart.toString())

                        if (checkRoom.value!!.isCreater == 1) {
                            //내가 방장임
                            Log.d("wlgusdnzzz", "내가방장")
                            isManager.value = true

                            if (checkRoom.value!!.room.isStart == 0) {
                                //내가 아직 방을시작하지 않음
                                Log.d("wlgusdnzzz", "방시작X")
                                isStarted.value = false
                            } else {
                                //내가 이미 방을 시작함
                                Log.d("wlgusdnzzz", "방시작O")
                                isStarted.value = true
                            }
                        } else {
                            //내가 방장X
                            isManager.value=false
                            if (checkRoom.value!!.room.isStart == 0) {
                                //방이 아직 시작하지 않음
                                isStarted.value = false
                                //임시
                            } else {
                                //방이 이미 시작됨
                                isStarted.value = true
                            }

                        }
                        isJoined.value = true
                        //MainActivity에서 변수 Observing함

                        progressBar.set(View.INVISIBLE)
                    } else {
                        progressBar.set(View.INVISIBLE)
                        //참가하는 방이 존재하지 않음
                        isJoined.value = false
                    }
                })
        }
        else
        {
            Log.d("wlgusdnzzz","아직 가입 X")
            isLogined.value=false
        }
    }

    fun attendRoom(roomNum : String)
    {
    //방에 참가하기
         if(sf.getString("kakao_token","null")=="null")
            {
                //아직 회원가입 및 로그인을 하지 않음
                //카카오링크를 타고 들어올 때 발생 가능
                isLogined.value=false
            }
        else
        {

            repository.attendRoom(roomNum.toLong(), sf.getString("kakao_token", "null"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(Action {
                    repository.checkRoom(sf.getString("kakao_token","null"))
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ t: CheckRoomList? ->
                            if(t?.checkRoomList!=null)
                            {
                                checkRoom.value = t!!.checkRoomList[0]
                                startDayText_joinroom2  = "${checkRoom.value!!.room.startDay} 에 시작합니다.\n친구들이 모일때까지 잠시 기다려주세요"
                                Log.d("wlgusdnzzz","${checkRoom.value!!.room.startDay}")
                                isSearched.value=true

                                //MainActivity에서 변수 Observing함
                            }
                            else
                            {
                                isSearched.value=false
                                //참가하는 방이 존재하지 않음
                            }
                        })
                },
                    Consumer { Log.d("wlgusdnzzz", "방참가못함") })
            //내가 참가한 방 정보 가져오기
            roomInfo.num.value = roomNum

        }

    }



    fun checkNitto()
    {
        checkNitto.value=true

    }

    fun findParticipants()
    {
        //api로 참가자들 정보 받아오기
    }

}