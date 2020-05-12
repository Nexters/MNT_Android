package com.nexters.frutto.service.model

import com.google.gson.annotations.SerializedName

class ApiResponse (
    @SerializedName("apiStatus")
    val apiStatus : ResponseResult
)