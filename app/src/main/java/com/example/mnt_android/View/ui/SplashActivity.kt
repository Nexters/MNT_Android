package com.example.mnt_android.view.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.mnt_android.R
import com.example.mnt_android.viewmodel.JoinRoomViewModel
import com.example.mnt_android.viewmodel.SplashViewModel

class SplashActivity : AppCompatActivity()
{
    lateinit var splashViewModel: SplashViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash)
        splashViewModel = ViewModelProviders.of(this)[SplashViewModel::class.java]


        Handler().postDelayed({
            if(splashViewModel.isEntered)
            {
                //입장한 방이 있다.

                var intent = Intent(this,JoinRoomActivity::class.java)
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
                startActivity(intent)
                finish()
            }
            else
            {
                var intent = Intent(this,LoginActivity::class.java)
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
                startActivity(intent)
                finish()
            }


        },2000)

    }
}