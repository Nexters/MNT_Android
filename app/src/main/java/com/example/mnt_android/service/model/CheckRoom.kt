package com.example.mnt_android.service.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class CheckRoom(
    @SerializedName("id")
    val id: Int,
    @SerializedName("isCreater")
    val isCreater: Int,
    @SerializedName("manittoId")
    val manittoId: String?,

    @SerializedName("room")
    val room : Room,

    @SerializedName("user")
    val user : User
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readParcelable(Room::class.java.classLoader),
        parcel.readParcelable(User::class.java.classLoader)
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeInt(isCreater)
        parcel.writeString(manittoId)
        parcel.writeParcelable(room, flags)
        parcel.writeParcelable(user, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CheckRoom> {
        override fun createFromParcel(parcel: Parcel): CheckRoom {
            return CheckRoom(parcel)
        }

        override fun newArray(size: Int): Array<CheckRoom?> {
            return arrayOfNulls(size)
        }
    }
}


