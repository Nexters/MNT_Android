package com.example.mnt_android.service.model

import com.google.gson.annotations.SerializedName

class MakeMission (
    @SerializedName("id")
    var id : Int,
    @SerializedName("isAbleImg")
    var isAbleImg : Int,
    @SerializedName("name")
    var name : String,
    @SerializedName("roomId")
    var roomId : Int
)