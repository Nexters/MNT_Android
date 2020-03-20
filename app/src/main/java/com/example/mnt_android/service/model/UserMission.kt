package com.example.mnt_android.service.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class UserMission(
    @SerializedName("content")
    var content : String,
    @SerializedName("id")
    var id : Int?,
    @SerializedName("missionId")
    var missionId: String?,
    @SerializedName("missionImg")
    var missionImg : String?,
    @SerializedName("roomId")
    var roomId : Int,
    @SerializedName("userDone")
    var userDone : Int,
    @SerializedName("userDoneTime")
    var userDoneTime : String,
    @SerializedName("userId")
    var user : User

) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readParcelable(User::class.java.classLoader)
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(content)
        parcel.writeInt(id ?: -1)
        parcel.writeString(missionId)
        parcel.writeString(missionImg)
        parcel.writeInt(roomId)
        parcel.writeInt(userDone)
        parcel.writeString(userDoneTime)
        parcel.writeParcelable(user, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UserMission> {
        override fun createFromParcel(parcel: Parcel): UserMission {
            return UserMission(parcel)
        }

        override fun newArray(size: Int): Array<UserMission?> {
            return arrayOfNulls(size)
        }
    }
}