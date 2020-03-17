package com.example.mnt_android.viewmodel

import android.content.res.Resources
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mnt_android.service.model.CreateMission
import com.kakao.auth.KakaoSDK.init
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.R
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.mnt_android.service.model.MakeMission
import com.example.mnt_android.service.model.Mission
import com.example.mnt_android.service.repository.DBRepository
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Action
import io.reactivex.schedulers.Schedulers


class CreateMissionViewModel(application: Application) : AndroidViewModel(application)
{
    val app = application
    var name : MutableLiveData<String> = MutableLiveData()
    var des : MutableLiveData<String> = MutableLiveData()
    var send : MutableLiveData<Boolean> = MutableLiveData()
    var fragmentNum : Int=0
    var isAbleImg : MutableLiveData<Int> = MutableLiveData()
    lateinit var missionDesArray  : Array<String>
    lateinit var missionNameArray  : Array<String>
    var isCreated : MutableLiveData<Boolean> = MutableLiveData()
    private val repository = DBRepository()

    init {

            missionDesArray = app.resources.getStringArray(com.example.mnt_android.R.array.arr_create_mission_des)
            missionNameArray = app.resources.getStringArray(com.example.mnt_android.R.array.arr_create_mission)

        isAbleImg.value=0

    }



    fun setMission(i : Int)
    {
        name.value=missionNameArray[i]
        des.value=missionDesArray[i]
    }



    fun makeMission(roomId : Long)
    {
        repository.makeMission(Mission(0, isAbleImg.value!!,name.value.toString(),roomId,null))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(Action {
                Log.d("wlgusdnzzz","미션 생성 성공")
                isCreated.value=true
            })
    }

    fun checkImage()
    {
        when(isAbleImg.value)
        {
            0->
            {
                isAbleImg.value=1
            }
            1->
            {
                isAbleImg.value=0
            }
        }
    }



}