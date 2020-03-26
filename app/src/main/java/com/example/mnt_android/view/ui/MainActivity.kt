package com.example.mnt_android.view.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.mnt_android.R
import com.example.mnt_android.databinding.ActivityMainBinding
import com.example.mnt_android.view.ui.LoginActivity.Companion.sf
import com.example.mnt_android.viewmodel.BackPressViewModel
import com.example.mnt_android.viewmodel.JoinRoomViewModel
import com.example.mnt_android.viewmodel.LoginViewModel

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity.kt"

    lateinit var backPressViewModel : BackPressViewModel
    lateinit var joinRoomViewModel : JoinRoomViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil
            .setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.lifecycleOwner = this


       backPressViewModel=  ViewModelProviders.of(this@MainActivity)[BackPressViewModel::class.java]
        binding.mainActivity=this@MainActivity
        joinRoomViewModel = ViewModelProviders.of(this@MainActivity)[JoinRoomViewModel::class.java]

        val sf = getSharedPreferences("login",0)
        val editor = sf.edit()


        userId = sf.getString("kakao_token","null")

        joinRoomViewModel.checkRoom()
        joinRoomViewModel.isLogined.observe(this, Observer {
            if(it==false)
            {
                var intent = Intent(this,LoginActivity::class.java)
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
                startActivity(intent)
                finish()
            }
        })
    }

    fun createroom()
    {
        val intent = Intent(this@MainActivity,CreateRoomActivity::class.java)
        startActivity(intent)
    }
    fun joinroom()
    {
        val intent = Intent(this@MainActivity,JoinRoomActivity::class.java)
        startActivity(intent)
    }

    override fun onBackPressed() {

        backPressViewModel.onBackPressed(this@MainActivity)

    }

    companion object
    {
        var userId : String="s"
    }

}
