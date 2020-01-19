package com.example.mnt_android.view.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.mnt_android.viewmodel.LoginViewModel
import com.kakao.auth.AuthType
import com.kakao.auth.ISessionCallback
import com.kakao.auth.Session
import com.kakao.network.ErrorResult
import com.kakao.usermgmt.StringSet.name
import com.kakao.usermgmt.UserManagement
import com.kakao.usermgmt.callback.MeV2ResponseCallback
import com.kakao.usermgmt.response.MeV2Response
import com.kakao.util.exception.KakaoException

class LoginActivity : AppCompatActivity()
{
    val TAG = "LoginActivity.kt"
    lateinit var callback : SessionCallback

    //여기서는 ViewModel을 사용하지 않는 이유 :
    // 어차피 finish()하기 떄문에 -> Activity가 onDestroy되면 생성한 ViewModel도 clear된다
    // 따라서 다음 Activity인 MainActivity에서 같은 ViewModel을 사용하지 못한다


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.mnt_android.R.layout.activity_login)



        callback = SessionCallback()
        Session.getCurrentSession().addCallback(callback)
        Session.getCurrentSession().checkAndImplicitOpen()

        sf = getSharedPreferences("login",0)
        editor = sf?.edit()







    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(Session.getCurrentSession().handleActivityResult(requestCode,resultCode,data))
        {
            return
        }
        super.onActivityResult(requestCode, resultCode, data)



    }

    override fun onDestroy() {
        super.onDestroy()
        Session.getCurrentSession().removeCallback(callback)
    }


    inner class SessionCallback() : ISessionCallback {

        val TAG = "SessionCallback"
        lateinit var id : String

        //카카오 로그인 Callback함수

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

                    Log.d(TAG,"name : ${result?.nickname} ")
                    Log.d(TAG,sf!!.getString("kakao","null"))


                    if(!sf!!.contains("kakao"))
                    {
                        editor?.putString("kakao",result?.id.toString())
                        editor?.commit()
                    }


                    val intent = Intent(this@LoginActivity,MainActivity::class.java)
                    intent.putExtra("nickname",result?.nickname)
                    startActivity(intent)
                    finish()

                }

            })

        }
    }

    companion object
    {
        var sf : SharedPreferences?=null
        var editor : SharedPreferences.Editor?=null
    }

}





