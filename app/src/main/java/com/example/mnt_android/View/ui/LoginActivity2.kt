package com.example.mnt_android.View.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.mnt_android.R
import com.example.mnt_android.databinding.ActivityLogin2Binding
import com.example.mnt_android.viewmodel.LoginViewModel

class LoginActivity2 : AppCompatActivity() {

    val TAG = "LoginActivity2.kt"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil
            .setContentView<ActivityLogin2Binding>(this, R.layout.activity_login2)
        binding.lifecycleOwner = this

        val loginViewModel =  ViewModelProviders.of(this)[LoginViewModel(application)::class.java]
        binding.loginViewModel = loginViewModel//layout의 binding 객체의 name = viewModel 에 viewModel을 초기화
        binding.loginActivity2 = this

    }
    fun check_login()
    {
        val intent = Intent(this@LoginActivity2, MainActivity::class.java)
        startActivity(intent)
    }
}
