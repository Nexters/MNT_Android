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

        joinRoomViewModel.checkRoom()
        val sf = getSharedPreferences("login", 0)

        userId = sf.getString("kakao_token","null")



        joinRoomViewModel.isJoined.observe(this, Observer {
            val intent = intent
            val str = intent.data
            if(it==true)
            {
                if(str!=null)
                {

                }
                    //참가한 방이 존재
                    if (joinRoomViewModel.isStarted.value == false) {
                        //방장이 방을 시작하지 않음
                        if (joinRoomViewModel.isManager.value == true) {
                            Toast.makeText(
                                this,
                                "방장임 CreateRoom의 Frag3으로 넘어가야함",
                                Toast.LENGTH_SHORT
                            ).show()

                            sf.edit().putBoolean("isManager", true)

                            val intent = Intent(this, CreateRoomActivity::class.java)
                            intent.putExtra("fragNum", 2)
                            intent.putExtra("checkRoom", joinRoomViewModel.checkRoom.value)
                            startActivity(intent)

                        } else {
                            val intent = Intent(this, JoinRoomActivity::class.java)
                            intent.putExtra("fragNum", 1)
                            intent.putExtra("checkRoom", joinRoomViewModel.checkRoom.value)
                            startActivity(intent)
                        }

                    } else {
                        //방장이 방을 시작함
                        if (joinRoomViewModel.isManager.value == true)
                            sf.edit().putBoolean("isManager", true)

                        if (sf.getBoolean("check", false)) {
                            //내 마니또를 확인했음
                            val intent = Intent(this, GameActivity::class.java)
                            startActivity(intent)
                        } else {
                            //내 마니또를 확인하지 못함
                            val intent = Intent(this, JoinRoomActivity::class.java)
                            intent.putExtra("fragNum", 2)
                            intent.putExtra("checkRoom", joinRoomViewModel.checkRoom.value)
                            startActivity(intent)
                        }
                    }
                    sf.edit().commit()

            }
            else
            {
                Toast.makeText(this,"참가한 방이 존재하지 않습니다.",Toast.LENGTH_SHORT).show()
                //참가한 방이 없다

                if(str!=null)
                {
                    val intent = Intent(this, JoinRoomActivity::class.java)
                    intent.putExtra("fragNum", 0)
                    intent.putExtra("roomNum", intent.data.getQueryParameter("roomnum"))
                    startActivity(intent)
                }
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
