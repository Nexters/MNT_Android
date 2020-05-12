package com.nexters.frutto.service.model

import android.os.Parcel
import android.os.Parcelable

data class Manitto(
    val id: String,
    val name: String,
    val fruttoId: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeInt(fruttoId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Manitto> {
        override fun createFromParcel(parcel: Parcel): Manitto {
            return Manitto(parcel)
        }

        override fun newArray(size: Int): Array<Manitto?> {
            return arrayOfNulls(size)
        }
    }
}