package com.example.mnt_android.service.model

import android.os.Parcel
import android.os.Parcelable
import android.util.EventLogTags
import com.google.gson.annotations.SerializedName
import java.util.*

class Room(

    @SerializedName("endDay")
    var endDay : String,
    @SerializedName("id")
    var id : Int,
    @SerializedName("isDone")
    var isDone :Int,
    @SerializedName("isStart")
    var isStart : Int,
    @SerializedName("maxPeople")
    var maxPeople : Int,
    @SerializedName("name")
    var name :String,
    @SerializedName("startDay")
    var startDay : String
) : Parcelable {


    constructor(parcel: Parcel) : this(
        parcel.readString(),
        //여기서 String to Date
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString()
        //여기서 String to Date
    /*
    * String from = "2013-04-08 10:10:10";

SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

Date to = transFormat.parse(from);
*/
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
