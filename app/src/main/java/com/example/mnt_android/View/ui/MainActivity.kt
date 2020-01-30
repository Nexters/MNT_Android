package com.example.mnt_android.View.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.mnt_android.R
import com.example.mnt_android.databinding.ActivityMainBinding
import com.example.mnt_android.viewmodel.BackPressViewModel
import com.example.mnt_android.viewmodel.LoginViewModel

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity.kt"

    lateinit var backPressViewModel : BackPressViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil
            .setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.lifecycleOwner = this

        val loginViewModel =  ViewModelProviders.of(this@MainActivity)[LoginViewModel::class.java]
        binding.loginViewModel = loginViewModel//layout의 binding 객체의 name = viewModel 에 viewModel을 초기화

       backPressViewModel=  ViewModelProviders.of(this@MainActivity)[BackPressViewModel::class.java]
        binding.mainActivity=this@MainActivity



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
