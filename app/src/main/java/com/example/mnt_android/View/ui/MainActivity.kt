package com.example.mnt_android.view.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.mnt_android.R
import com.example.mnt_android.databinding.ActivityMainBinding
import com.example.mnt_android.viewmodel.LoginViewModel

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity.kt"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val binding = DataBindingUtil
            .setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.lifecycleOwner = this

        val loginViewModel =  ViewModelProviders.of(this)[LoginViewModel::class.java]
        binding.loginViewModel = loginViewModel//layout의 binding 객체의 name = viewModel 에 viewModel을 초기화

        loginViewModel.nickname.value = intent.getStringExtra("nickname")
        //다시


    }
}
