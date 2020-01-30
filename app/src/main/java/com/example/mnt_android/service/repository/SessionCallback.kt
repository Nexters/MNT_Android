package com.example.mnt_android.service.repository

import android.app.Application
import android.util.Log
import com.example.mnt_android.service.model.KakaoUser
import com.example.mnt_android.View.ui.LoginActivity
import com.kakao.auth.ISessionCallback
import com.kakao.network.ErrorResult
import com.kakao.usermgmt.UserManagement
import com.kakao.usermgmt.callback.MeV2ResponseCallback
import com.kakao.usermgmt.response.MeV2Response
import com.kakao.util.exception.KakaoException

class SessionCallback(application : Application) : ISessionCallback {

    val TAG = "SessionCallback"
    lateinit var user : KakaoUser
    val app  = application

    init {
        LoginActivity.sf = app.getSharedPreferences("login",0)
        LoginActivity.editor = LoginActivity.sf?.edit()
        if(LoginActivity.sf!!.contains("kakao_nickname"))
            user = KakaoUser(LoginActivity.sf!!.getString("kakao_id","null"),LoginActivity.sf!!.getString("kakao_nickname","null"))
        else
        {
            user = KakaoUser("null","null")
        }
    }


    override fun onSessionOpenFailed(exception: KakaoException?) {

    }

    override fun onSessionOpened() {
        UserManagement.getInstance().me(object : MeV2ResponseCallback() {


            override fun onFailure(errorResult: ErrorResult?) {
                Log.e(TAG,"Fail")
            }

            override fun onSessionClosed(errorResult: ErrorResult?) {
                Log.d(TAG,"closed")
            }

            override fun onSuccess(result: MeV2Response?) {


                if(!LoginActivity.sf!!.contains("kakao_id"))
                {
                    LoginActivity.editor?.putString("kakao_id",result?.id.toString())
                    LoginActivity.editor?.putString("kakao_nickname",result?.nickname)
                    LoginActivity.editor?.commit()
                    user.id = result?.id.toString()
                    user.nickname.value=result?.nickname
                }

/*

*/
            }

        })

    }
}