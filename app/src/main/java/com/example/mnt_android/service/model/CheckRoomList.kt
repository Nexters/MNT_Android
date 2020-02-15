package com.example.mnt_android.service.model

import com.google.gson.annotations.SerializedName

class CheckRoomList(
    @SerializedName("data")
    val checkRoomList : List<CheckRoom>
)