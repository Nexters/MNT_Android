package com.example.mnt_android.viewmodel

import android.graphics.Bitmap
import android.icu.text.SimpleDateFormat
import android.os.Build
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mnt_android.service.model.DoMission
import com.example.mnt_android.service.model.User
import com.example.mnt_android.service.model.UserMission
import com.example.mnt_android.service.repository.DBRepository
import com.example.mnt_android.view.ui.MainActivity.Companion.userId
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Action
import io.reactivex.schedulers.Schedulers
import java.util.*

class DoMissionViewModel : ViewModel()
{


    var fragmentNum = 0
    var missionText : MutableLiveData<String> = MutableLiveData()
    var missionId : Long = 0
    var roomId : Int = 0
    var date : Date = Date()
    var isSended : MutableLiveData<Boolean> = MutableLiveData()
    private val repository = DBRepository()
    var bitmap : Bitmap?=null
    init {
        //넘겨받은 값으로 초기화

        isSended.value=false

    }

    fun sendMission()
    {
        val today = Date()
        var strdate: String? = null

        var format1 = SimpleDateFormat()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {

            format1 = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
            strdate = format1.format(today)
        }

        missionId=75
        repository.sendMission(
            UserMission(missionText.value!!,0,null,"image",roomId,0,strdate!!, User("",userId,"","")),missionId).
            subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(Action {
                Log.d("wlgusdnzzz","미션 수행 성공")
                isSended.value=true
            })


    }



}