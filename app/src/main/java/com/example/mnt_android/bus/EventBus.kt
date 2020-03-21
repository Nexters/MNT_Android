package com.example.mnt_android.bus

import io.reactivex.subjects.PublishSubject

// 메뉴바 이벤트 버스
const val SWIPE_NOT = -1
const val SWIPE_DOWN = 0
const val SWIPE_UP = 1

val swipeEventBus = PublishSubject.create<Int>()
fun sendSwipeEvent(swipe: Int) {
    swipeEventBus.onNext(swipe)
}


// 필터링 이벤트 버스
const val MISSION_LIST_ALL = "0"
const val MISSION_LIST_TO_ME = "1"
const val MISSION_LIST_FROM_ME = "2"
const val MISSION_LIST_MISSION_TYPE = "10"
const val MISSION_LIST_PARTICIPANT = "11"

val filteringEventBus = PublishSubject.create<Array<String>>()
fun sendFilteringEvent(filterType: Array<String>) {
    filteringEventBus.onNext(filterType)
}