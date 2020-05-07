package com.nexters.frutto.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.nexters.frutto.service.model.Mission
import com.nexters.frutto.service.repository.DBRepository
import com.nexters.frutto.service.repository.PreferencesRepository
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
    private val pr = PreferencesRepository(app.baseContext)
    private val repository = DBRepository()

    init {

            missionDesArray = app.resources.getStringArray(com.nexters.frutto.R.array.arr_create_mission_des)
            missionNameArray = app.resources.getStringArray(com.nexters.frutto.R.array.arr_create_mission)

        isAbleImg.value=0

    }



    fun setMission(i : Int)
    {
        name.value=missionNameArray[i]
        des.value=missionDesArray[i]
    }



    fun makeMission()
    {
        repository.makeMission(Mission(0, isAbleImg.value!!,name.value.toString(),pr.getRoomId(),null))
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