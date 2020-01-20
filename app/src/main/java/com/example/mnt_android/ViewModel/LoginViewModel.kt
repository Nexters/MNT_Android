


package com.example.mnt_android.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.mnt_android.service.model.KakaoUser
import com.example.mnt_android.service.repository.SessionCallback
import com.kakao.auth.Session

class LoginViewModel(application: Application) : AndroidViewModel(application)
{
    val app = application
    lateinit var user : KakaoUser
    lateinit var callback : SessionCallback

   init {
       callback = SessionCallback(app)
       Session.getCurrentSession().addCallback(callback)
       Session.getCurrentSession().checkAndImplicitOpen()
       user = callback.user

   }

    override fun onCleared() {
        super.onCleared()

        Session.getCurrentSession().removeCallback(callback)
    }





}