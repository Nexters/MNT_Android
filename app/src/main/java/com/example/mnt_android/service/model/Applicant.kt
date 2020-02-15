package com.example.mnt_android.service.model

data class Applicant(
    val id: Int,
    val room: Room,
    val user: User,
    val manittoId: String,
    val isCreater: Int
)