package com.example.mnt_android.service.model

class CheckRoom(
    val id: Int,
    val isCreater: Int,
    val manittoId: String,
    val room : Room,
    val user : User
)


class User(val id : String,
           val name : String,
           val profilePic : String)