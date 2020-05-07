package com.nexters.frutto.service.model

import android.graphics.Bitmap
import android.os.Parcel
import android.os.Parcelable

class DoMission() : Parcelable
{
    lateinit var name : String
    lateinit var description : String
    lateinit var textMission : String
    var imgMission : Bitmap?=null

    constructor(name : String, des : String, text : String) : this() {
        this.name=name
        this.description=des
        this.textMission=text
    }

    constructor(parcel: Parcel) : this() {
        name = parcel.readString()
        description = parcel.readString()
        textMission = parcel.readString()
        imgMission = parcel.readParcelable(Bitmap::class.java.classLoader)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(description)
        parcel.writeString(textMission)
        parcel.writeParcelable(imgMission, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DoMission> {
        override fun createFromParcel(parcel: Parcel): DoMission {
            return DoMission(parcel)
        }

        override fun newArray(size: Int): Array<DoMission?> {
            return arrayOfNulls(size)
        }
    }


}