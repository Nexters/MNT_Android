package com.example.mnt_android.service.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class UserMissionResponse (
    @SerializedName("manitto")
    var manitto : Manitto?,//내 마니또 정보
    @SerializedName("missionId")
    var missionId : Int,
    @SerializedName("missionName")
    var missionName: String,
    @SerializedName("userFruttoId")//나의 넘버
    var userFruttoId : Int,
    @SerializedName("userMission")
    var userMission : UserMission,
    @SerializedName("isAbleImg")
var isAbleImg : Int

) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readParcelable(Manitto::class.java.classLoader),
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readParcelable(UserMission::class.java.classLoader),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(manitto, flags)
        parcel.writeInt(missionId)
        parcel.writeString(missionName)
        parcel.writeInt(userFruttoId)
        parcel.writeParcelable(userMission, flags)
        parcel.writeInt(isAbleImg)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UserMissionResponse> {
        override fun createFromParcel(parcel: Parcel): UserMissionResponse {
            return UserMissionResponse(parcel)
        }

        override fun newArray(size: Int): Array<UserMissionResponse?> {
            return arrayOfNulls(size)
        }
    }
}