package com.example.mnt_android.service.model

import com.google.gson.annotations.SerializedName
import java.util.*

class SendRoom (
    var endDay : Date,
    var id : Int,
    var isDone :Int,
    var isStart : Int,
    var maxPeople : Int,
    var name :String,
    var startDay : Date
)