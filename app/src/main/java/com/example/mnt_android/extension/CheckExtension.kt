package com.example.mnt_android.extension

import com.example.mnt_android.util.FALSE_INT
import com.example.mnt_android.util.TRUE_INT
import java.text.SimpleDateFormat
import java.util.*


val Float.isPositive: Boolean
    get() = this > 0
val Float.isNegative: Boolean
    get() = this < 0

val Int.isTrue: Boolean
    get() = this == TRUE_INT
val Int.isFalse: Boolean
    get() = this == FALSE_INT

val String.checkUploadDate: String
    get() {
        val MD_FORMAT = "%02d"
        val calendar = Calendar.getInstance()

        val todayDateStr = "${calendar.get(Calendar.YEAR)}${MD_FORMAT.format(calendar.get(Calendar.MONTH)+1)}${MD_FORMAT.format(calendar.get(Calendar.DAY_OF_MONTH))}"
        val updateDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.KOREA)
        val updateDate = updateDateFormat.parse(this.split("T")[0])
        calendar.time = updateDate
        val updateDateStr = "${calendar.get(Calendar.YEAR)}${MD_FORMAT.format(calendar.get(Calendar.MONTH)+1)}${MD_FORMAT.format(calendar.get(Calendar.DAY_OF_MONTH))}"

        return when(todayDateStr.toInt() - updateDateStr.toInt()) {
            0 -> "오늘"
            else -> SimpleDateFormat("MM월 dd일", Locale.KOREA).format(updateDate)
        }
    }