package com.example.mnt_android.bus

import io.reactivex.subjects.PublishSubject

val scrollEventBus = PublishSubject.create<Int>()