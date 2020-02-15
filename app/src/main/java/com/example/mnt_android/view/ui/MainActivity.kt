package com.example.mnt_android.view.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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

        joinRoomViewModel.checkRoom()

        joinRoomViewModel.isJoined.observe(this, Observer {
            if(it==true)
            {
                //참가한 방이 존재
                if(joinRoomViewModel.isStarted.value==false)
                {
                    //방장이 방을 시작하지 않음
                    val intent = Intent(this, JoinRoomActivity::class.java)
                    intent.putExtra("fragNum", 1)
                    intent.putExtra("checkRoom", joinRoomViewModel.checkRoom.value)
                    startActivity(intent)
                }
                else
                {
                    //방장이 방을 시작함
                    val sf = getSharedPreferences("login", 0)

                    if (sf.getBoolean("check", false))
                    {
                        //내 마니또를 확인했음
                         val intent = Intent(this, GameActivity::class.java)
                        startActivity(intent)
                    }
                    else
                    {
                        //내 마니또를 확인하지 못함
                        val intent = Intent(this, JoinRoomActivity::class.java)
                        intent.putExtra("fragNum", 2)
                        intent.putExtra("checkRoom", joinRoomViewModel.checkRoom.value)
                        startActivity(intent)
                    }
                }
            }
            else
            {
                //참가한 방이 없다
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
}
