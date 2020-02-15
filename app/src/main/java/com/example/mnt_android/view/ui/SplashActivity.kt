package com.example.mnt_android.view.ui

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.mnt_android.R
import com.example.mnt_android.viewmodel.SplashViewModel

class SplashActivity : AppCompatActivity()
{
    lateinit var splashViewModel: SplashViewModel

    var editor : SharedPreferences.Editor?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash)
        splashViewModel = ViewModelProviders.of(this)[SplashViewModel::class.java]

        var sf =getSharedPreferences("login",0)
        Handler().postDelayed({
            if(sf.getString("kakao_token","null")!="null")
            {
                //이미 로그인을 했었다
                Log.d("wlgusdnzzz",sf.getString("kakao_token","null"))

                var intent = Intent(this,MainActivity::class.java)
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
                startActivity(intent)
                finish()
            }
            else
            {
                //한번도 로그인을 하지 않았다
                var intent = Intent(this,LoginActivity::class.java)
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
                startActivity(intent)
                finish()
            }


        },2000)

    }
}