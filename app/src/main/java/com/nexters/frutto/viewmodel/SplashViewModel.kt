package com.nexters.frutto.viewmodel

import androidx.lifecycle.ViewModel
import com.nexters.frutto.service.repository.DBRepository

class SplashViewModel : ViewModel()
{

    private val repository = DBRepository()
    var isEntered = false

    init {
        //여기서 참가한 방이 있는지 확인하는 API 호출
        isEntered=false
    }




}