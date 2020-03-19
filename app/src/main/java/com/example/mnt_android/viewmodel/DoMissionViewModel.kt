package com.example.mnt_android.viewmodel

import android.app.Application
import android.graphics.Bitmap
import android.icu.text.SimpleDateFormat
import android.os.Build
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mnt_android.service.model.DoMission
import com.example.mnt_android.service.model.User
import com.example.mnt_android.service.model.UserMission
import com.example.mnt_android.service.model.UserMissionResponse
import com.example.mnt_android.service.repository.DBRepository
import com.example.mnt_android.view.ui.MainActivity.Companion.userId
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Action
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.util.*

class DoMissionViewModel(application : Application) : AndroidViewModel(application)
{

    val app = application
    var fragmentNum = 0
    var missionText : MutableLiveData<String> = MutableLiveData()
    var roomId : Long = 0
    var isSended : MutableLiveData<Boolean> = MutableLiveData()
    private val repository = DBRepository()
    var file : File?=null
    var bitmap : Bitmap?=null
    var nowUserMission = MutableLiveData<UserMissionResponse>()
    var missionDescription = ""
    init {
        //넘겨받은 값으로 초기화

        isSended.value=false
        roomId = app.getSharedPreferences("login",0).getLong("roomId",0)
        userId = app.getSharedPreferences("login",0).getString("kakao_token","")

        Log.d("wlgusdnzzz","roomId : $roomId  userId : $userId")

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


        Log.d("wlgusdnzzz","roomId : $roomId  userId : $userId")
        Log.d("wlgusdnzzz","missionId : ${nowUserMission.value!!.missionId}  missionText : ${missionText.value}")

        //var requestFile = RequestBody.create(MediaType.parse("multipart/form-data"),file)
       // var body = MultipartBody.Part.createFormData("img",file?.name,requestFile)
        repository.sendMission(roomId,userId,nowUserMission.value!!.missionId,missionText.value!!.toString(),file)
           .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d("wlgusdnzzz","미션 수행 성공")
                isSended.value=true
            },{
                Log.e("wlgusdnzzz",it.toString())
            })


    }



}