package com.nexters.frutto.service.model

import com.google.gson.annotations.SerializedName

class GetUserMission (
    @SerializedName("data")
    var userMissionResponses : ArrayList<UserMissionResponse>
)