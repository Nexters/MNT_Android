package com.example.mnt_android.service.model

import com.google.gson.annotations.SerializedName

class ApiResponse (
    @SerializedName("apiStatus")
    val apiStatus : ApiStatus
)