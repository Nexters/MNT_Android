package com.example.mnt_android.bus

import io.reactivex.subjects.PublishSubject

const val SWIPE_NOT = -1
const val SWIPE_DOWN = 0
const val SWIPE_UP = 1

val swipeEventBus = PublishSubject.create<Int>()
fun sendSwipeEvent(swipe: Int) {
    swipeEventBus.onNext(swipe)
}