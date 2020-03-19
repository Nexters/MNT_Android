package com.example.mnt_android.service.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class CheckRoom(
    @SerializedName("fruttoId")
    val fruttoId: Int,//나의 프루또 Id
    @SerializedName("isCreater")
    val isCreater: Int,
    @SerializedName("manittoFruttoId")
    val manittoFruttoId: String?,//내 마니또의 프루또 Id
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
        parcel.writeInt(fruttoId)
        parcel.writeInt(isCreater)
        parcel.writeString(manittoFruttoId)
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

