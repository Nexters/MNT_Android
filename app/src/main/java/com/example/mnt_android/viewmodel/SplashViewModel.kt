package com.example.mnt_android.viewmodel

import androidx.lifecycle.ViewModel

class SplashViewModel : ViewModel()
{

    var isEntered = false

    init {
        //여기서 참가한 방이 있는지 확인하는 API 호출
        isEntered=true
    }



}