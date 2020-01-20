package com.example.mnt_android.service.model

import androidx.lifecycle.MutableLiveData

class KakaoUser
{
   var id : String
    var nickname : MutableLiveData<String> = MutableLiveData()

   constructor(id : String,nickname : String)
   {
       this.id = id
       this.nickname.value = nickname
   }

}