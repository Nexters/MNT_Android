package com.nexters.frutto.service.model

data class MissionListResponse(
    val data: ArrayList<UserMissionResponse>,
    val apiStatus: ResponseResult
)