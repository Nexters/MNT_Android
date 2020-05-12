package com.nexters.frutto.service.model

data class Applicant(
    val isCreater: Int,
    val manitto: UserResponse,
    val user: User,
    val userFruttoId: Int,
    val room: Room
)