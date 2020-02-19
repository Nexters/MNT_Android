package com.example.mnt_android.extension

import com.example.mnt_android.util.FALSE_INT
import com.example.mnt_android.util.TRUE_INT


val Float.isPositive: Boolean
    get() = this > 0
val Float.isNegative: Boolean
    get() = this < 0

val Int.isTrue: Boolean
    get() = this == TRUE_INT
val Int.isFalse: Boolean
    get() = this == FALSE_INT