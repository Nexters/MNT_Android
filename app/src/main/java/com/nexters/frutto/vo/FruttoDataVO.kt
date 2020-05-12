package com.nexters.frutto.vo

data class FruttoDataVO(
    val id: Int,
    val koreanName: String,
    val koreanNickName: String,
    val englishName: String,
    val backgroundFruit1: String,
    val backgroundFruit2: String
) {
    fun isFrutto(_id: Int) = id == _id
}