package com.example.mnt_android.service.model

data class MissionListResponse(
    val data: ArrayList<UserMissionResponse>,
    val apiStatus: ResponseResult
)