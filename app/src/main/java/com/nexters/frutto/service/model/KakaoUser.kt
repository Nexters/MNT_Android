package com.nexters.frutto.service.model

import androidx.lifecycle.MutableLiveData

class KakaoUser
{
   var id : String
    var nickname : MutableLiveData<String> = MutableLiveData()
    var token : String


   constructor(id : String,nickname : String,token : String)
   {
       this.id = id
       this.nickname.value = nickname
       this.token=token
   }

}