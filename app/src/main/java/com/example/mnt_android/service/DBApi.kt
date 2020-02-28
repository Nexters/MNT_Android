package com.example.mnt_android.service

import com.example.mnt_android.service.model.*
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import io.reactivex.Flowable

import io.reactivex.Completable
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
        @Body room : SendRoom,
        @Query("userId") userId : String
    ):  Flowable<RoomId>

    //방 참가
    @GET("api/room/attend/{roomId}")
    fun attendRoom(
        @Path("roomId") roomId : Long ,
        @Query("userId") userId: String
    ): Completable

    // 마니또 방 시작
    @GET("/api/room/start/{roomId}")
    fun startRoom(
        @Path("roomId") roomId: Long
    ) : Completable

    // 참여자 리스트
    @GET("/api/room/user-list/{roomId}")
    fun userList(
        @Path("roomId") roomId: Long
    ) : Single<ApplicantResponse>

    // 참여자 삭제
    @DELETE("/api/room/user")
    fun exitUser(
        @Query("roomId") roomId: Long,
        @Query("userId") userId: String
    ) : Completable

    @POST("/api/mission/make")
    fun makeMission(
        @Body mission : Mission
    ) : Completable

    @POST("/api/mission/send")
    fun sendMission(
        @Body userMission : UserMission,
        @Query("missionId") missionId : Long
    ) : Completable

}