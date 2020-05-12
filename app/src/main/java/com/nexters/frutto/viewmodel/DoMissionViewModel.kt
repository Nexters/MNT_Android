package com.nexters.frutto.viewmodel

import android.app.Application
import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.nexters.frutto.R
import com.nexters.frutto.service.model.*
import com.nexters.frutto.service.repository.DBRepository
import com.nexters.frutto.service.repository.PreferencesRepository
import com.nexters.frutto.view.ui.MainActivity.Companion.userId
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class DoMissionViewModel(application : Application) : AndroidViewModel(application)
{

    private val app = application
    private val repository = DBRepository()
    private val pr = PreferencesRepository(app)
    private val disposable = CompositeDisposable()
    var fragmentNum = 0
    var missionText : MutableLiveData<String> = MutableLiveData("")
    var roomId : Long = 0
    var isSended : MutableLiveData<Boolean> = MutableLiveData()
    var file : File?=null
    var bitmap : Bitmap?=null
    var nowUserMission = MutableLiveData<UserMissionResponse>()
    var missionDescription = ""
    var imageButtonText = MutableLiveData<String>(app.getString(R.string.tv_get_image))
    init {
        //넘겨받은 값으로 초기화

        isSended.value=false
        roomId = pr.getRoomId()
        userId = pr.getUserId()
    }
    fun sendMission()
    {

        var imgFile: MultipartBody.Part? = null
        file?.let {
            imgFile = MultipartBody.Part.createFormData(
                "img",
                it.name ?: "",
                RequestBody.create(MediaType.parse("img/png"), it)
            )
        }

        disposable.add(
            repository.sendMission(
                roomId,
                userId,
                nowUserMission.value!!.missionId.toLong(),
                missionText.value!!.toString(),
                imgFile
            )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.d("wlgusdnzzz", "미션 수행 성공")
                    isSended.value = true
                }, {
                    Log.e("wlgusdnzzz", it.toString())
                })
        )
    }

    fun getUserFruttoId() = pr.getFruttoId()
    fun getManitoFruttoId() = pr.getManitoFruttoId()
    fun getManitoNm() = pr.getManitoNm()

    override fun onCleared() {
        disposable.clear()
        super.onCleared()
    }
}