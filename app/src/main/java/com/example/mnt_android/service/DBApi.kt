package com.example.mnt_android.service

import com.example.mnt_android.service.model.CheckRoom
import com.example.mnt_android.service.model.Room
import com.google.gson.JsonElement
import com.kakao.usermgmt.response.model.User
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single
import retrofit2.http.*

interface DBApi {

    //회원가입
    @PUT("api/user/sign-up")
    fun signUp(
        @Path("id") id: Int,
        @Path("name") name: String,
        @Path("profilePic") profilePic: String,
        @Path("fcmToken") fcmToken: String
    ): Completable

    //방 유무확인
    @GET("api/room/check")
    fun checkRoom(
        @Query("userId") userId: Int
    ): Single<CheckRoom>

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
        @Query("userId") userId: Int
    ): Single<Room>



}