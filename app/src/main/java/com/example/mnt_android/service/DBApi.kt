package com.example.mnt_android.service

import com.example.mnt_android.service.model.CheckRoom
import com.example.mnt_android.service.model.CheckRoomList
import com.example.mnt_android.service.model.Room
import com.example.mnt_android.service.model.User
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single
import retrofit2.http.*

interface DBApi {

    //회원가입

    @POST("api/user/sign-up")
    fun signUp(
        @Body user : User
    ): Completable

    //방 유무확인
    @GET("api/room/check")
    fun checkRoom(
        @Header("userId") userId: String
    ): Flowable<CheckRoomList>

    //방 생성
    @POST("api/room/make")
    fun createRoom(
        @Path("endDay") endDay: String,
        @Path("id") id: Int,
        @Path("isDone") isDone : Int,
        @Path("isStart") isStart : Int,
        @Path("maxPeople") maxPeople : Int,
        @Path("name") name : String,
        @Path("startDay") startDay : String
    ): Completable


    //방 참가
    @GET("api/room/attend/{roomId}")
    fun attendRoom(
        @Path("roomId") roomId : Int ,
        @Query("userId") userId: String
    ): Completable



}