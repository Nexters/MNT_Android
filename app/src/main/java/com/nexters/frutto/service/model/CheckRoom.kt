package com.nexters.frutto.service.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class CheckRoom(

    @SerializedName("isCreater")
    val isCreater: Int,
    @SerializedName("manitto")
    val manitto: UserResponse?,//내 마니또의 프루또 Id
    @SerializedName("room")
    val room : Room,
    @SerializedName("user")
    val user : User,
    @SerializedName("userFruttoId")
    val userFruttoId: Int?//나의 프루또 Id
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readParcelable(UserResponse::class.java.classLoader),
        parcel.readParcelable(Room::class.java.classLoader),
        parcel.readParcelable(User::class.java.classLoader),
        parcel.readValue(Int::class.java.classLoader) as? Int
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(isCreater)
        parcel.writeParcelable(manitto, flags)
        parcel.writeParcelable(room, flags)
        parcel.writeParcelable(user, flags)
        parcel.writeValue(userFruttoId)
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