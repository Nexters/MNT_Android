


package com.example.mnt_android.viewmodel

import android.app.Application
import android.content.Intent
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat.startActivity
import androidx.databinding.ObservableInt
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.mnt_android.service.model.CheckRoom
import com.example.mnt_android.service.model.KakaoUser
import com.example.mnt_android.service.model.User
import com.example.mnt_android.service.repository.DBRepository
import com.example.mnt_android.service.repository.SessionCallback
import com.example.mnt_android.view.ui.LoginActivity
import com.example.mnt_android.view.ui.MainActivity
import com.google.firebase.iid.FirebaseInstanceId
import com.google.gson.JsonObject
import com.kakao.auth.AuthType
import com.kakao.auth.Session
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

class LoginViewModel(application: Application) : AndroidViewModel(application)
{
    val app = application
    var kuser : KakaoUser
    lateinit var user : User
    var callback : SessionCallback
    var fragmentNum = 0
    var isHaveRoom : MutableLiveData<Int> = MutableLiveData()
    var isLogined : MutableLiveData<Boolean> = MutableLiveData()
    var top_login2 : String = ""
    private val repository = DBRepository()

   init {
       //자동로그인
       callback = SessionCallback(app)
       Session.getCurrentSession().addCallback(callback)
       Session.getCurrentSession().checkAndImplicitOpen()
       kuser = callback.user
       top_login2 = kuser.nickname.value.toString()+"님, 반가워요!"
    }
    fun createAccount()
    {
        LoginActivity.kuser=kuser
        repository.signUp(User(FirebaseInstanceId.getInstance().token!!,kuser.token,kuser.nickname.value!!,"pic"))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(Action {
                //로그인 성공
                top_login2 = kuser.nickname.value.toString()+"님, 반가워요!"
                Log.d("wlgusdnzzz",top_login2)
                isLogined.value=true
            },
                Consumer {
                    //로그인 실패
                    isLogined.value=false
                })
    }

    override fun onCleared() {
        super.onCleared()

        Session.getCurrentSession().removeCallback(callback)
    }
}