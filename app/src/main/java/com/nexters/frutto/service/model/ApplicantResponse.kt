package com.nexters.frutto.service.model

data class ApplicantResponse(
    val data: ArrayList<Applicant>,
    val apiStatus: ResponseResult
)