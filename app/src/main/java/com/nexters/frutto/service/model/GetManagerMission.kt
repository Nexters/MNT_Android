package com.nexters.frutto.service.model

import com.google.gson.annotations.SerializedName

class GetManagerMission(
    @SerializedName("data")
    var missionResponses : ArrayList<MissionResponse>
)