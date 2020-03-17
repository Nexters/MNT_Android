package com.example.mnt_android.service.model

import com.google.gson.annotations.SerializedName

class MissionResponse (
    @SerializedName("id")
    var id : Int,//미션 Id
    @SerializedName("isAbleImg")
    var isAbleImg : Int,
    @SerializedName("name")
    var missionName : String,
    @SerializedName("roomId")
    var roomId : Int,
    @SerializedName("userMissions")
    var userMissions : Array<UserMissionResponse>//이 미션에 대한 모든 사용자의 DataList
)