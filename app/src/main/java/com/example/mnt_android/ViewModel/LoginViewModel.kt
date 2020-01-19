


package com.example.mnt_android.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel(application: Application) : AndroidViewModel(application)
{

    var nickname : MutableLiveData<String> = MutableLiveData()





}