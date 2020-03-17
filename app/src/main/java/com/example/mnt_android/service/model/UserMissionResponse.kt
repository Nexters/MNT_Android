package com.example.mnt_android.service.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class UserMissionResponse (
    @SerializedName("manitto")
    var manitto : User,//내 마니또 정보
    @SerializedName("manittoFruttoId")//내 마니또의 넘버
    var manittoFruttoId : Int,
    @SerializedName("missionId")
    var missionId : Int,
    @SerializedName("missionName")
    var missionName: String,
    @SerializedName("userFruttoId")//나의 넘버
    var userFruttoId : Int,
    @SerializedName("userMission")
    var userMission : UserMission
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readParcelable(User::class.java.classLoader),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readParcelable(UserMission::class.java.classLoader)
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(manitto, flags)
        parcel.writeInt(manittoFruttoId)
        parcel.writeInt(missionId)
        parcel.writeString(missionName)
        parcel.writeInt(userFruttoId)
        parcel.writeParcelable(userMission, flags)
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