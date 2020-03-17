package com.example.mnt_android.service.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class GetUserMission (
    @SerializedName("data")
    var userMissionResponses : ArrayList<UserMissionResponse>
)