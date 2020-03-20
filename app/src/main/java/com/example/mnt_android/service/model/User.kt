package com.example.mnt_android.service.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import org.intellij.lang.annotations.Identifier
import java.util.*
import java.util.*

class User(

    @SerializedName("fcmToken")
    val fcmToken: String?,

    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name : String,
    @SerializedName("profilePic")
    val profilePic: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(fcmToken)
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeString(profilePic)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }
}

