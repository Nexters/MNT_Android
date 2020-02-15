package com.example.mnt_android.service.model

import android.os.Parcel
import android.os.Parcelable
import android.util.EventLogTags
import com.google.gson.annotations.SerializedName

class Room(
    @SerializedName("endDay")
    val endDay : String,
    @SerializedName("id")
    val id : Int,
    @SerializedName("isDone")
    val isDone :Int,
    @SerializedName("isStart")
    val isStart : Int,
    @SerializedName("maxPeople")
    val maxPeople : Int,
    @SerializedName("name")
    val name :String,
    @SerializedName("startDay")
    val startDay : String) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(endDay)
        parcel.writeInt(id)
        parcel.writeInt(isDone)
        parcel.writeInt(isStart)
        parcel.writeInt(maxPeople)
        parcel.writeString(name)
        parcel.writeString(startDay)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Room> {
        override fun createFromParcel(parcel: Parcel): Room {
            return Room(parcel)
        }

        override fun newArray(size: Int): Array<Room?> {
            return arrayOfNulls(size)
        }
    }
}
