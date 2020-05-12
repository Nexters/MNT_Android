package com.nexters.frutto.service.model

import com.google.gson.annotations.SerializedName

class CheckRoomList(
    @SerializedName("data")
    val checkRoomList : List<CheckRoom>
)