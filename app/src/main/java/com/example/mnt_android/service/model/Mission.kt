package com.example.mnt_android.service.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class Mission (
    @SerializedName("id")
    var id : Long,
    @SerializedName("isAbleImg")
    var isAbleImg : Int,
    @SerializedName("name")
    var name : String,
    @SerializedName("roomId")
    var roomId : Long,
    @SerializedName("userMission")
    var userMissions : Array<UserMission>?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readLong(),
        parcel.createTypedArray(UserMission)
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeInt(isAbleImg)
        parcel.writeString(name)
        parcel.writeLong(roomId)
        parcel.writeTypedArray(userMissions, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Mission> {
        override fun createFromParcel(parcel: Parcel): Mission {
            return Mission(parcel)
        }

        override fun newArray(size: Int): Array<Mission?> {
            return arrayOfNulls(size)
        }
    }
}