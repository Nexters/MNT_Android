package com.example.mnt_android.service

import com.example.mnt_android.service.model.*
import io.reactivex.Flowable

import io.reactivex.Completable
import io.reactivex.Single
import okhttp3.MultipartBody
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

    //방 종료
    @GET("api/room/end/{roomId}")
    fun endRoom(
        @Path("roomId") roomId : Long
    ): Completable

    // 마니또 방 시작
    @GET("/api/room/start/{roomId}")
    fun startRoom(
        @Path("roomId") roomId: Long
    ) : Single<ApiResponse>

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
    ) : Flowable<ApiResponse>

    @POST("/api/mission/make")
    fun makeMission(
        @Body mission : Mission
    ) : Completable

    @GET("/api/user/manitto")
    fun getManitto(
        @Query("roomId") roomId : Long,
        @Query("userId") userId: String
    ) : Flowable<UserResponseResult>

    @Multipart
    @Headers("Accept: */*", "Accept-Encoding: gzip,deflate")
    @POST("/api/mission/send")
    fun sendMission(
        @Query("roomId") roomId  : Long,
        @Query("userId") userId : String,
        @Query("missionId") missionId : Long,
        @Query("content") content : String,
        @Part img : MultipartBody.Part
    ) : Completable

    @POST("/api/mission/send")
    fun sendMission(
        @Query("roomId") roomId  : Long,
        @Query("userId") userId : String,
        @Query("missionId") missionId : Long,
        @Query("content") content : String
    ) : Completable

    @GET("/api/mission/done/{userId}")
    fun getUserMission(
        @Path("userId") userId : String,
        @Query("roomId") roomId : Long
    ) : Flowable<MissionListResponse>

    @GET("/api/mission/list/order-mission/{roomId}")
    fun groupByMission(
        @Path("roomId") roomId : Long
    ) : Flowable<GetManagerMission>

    @GET("/api/mission/list/{roomId}")
    fun getMissionList(
        @Path("roomId") roomId : Long
    ) : Flowable<MissionListResponse>

    @GET("/api/mission/dashBoard/{roll}")
    fun getDashBoardData(
        @Path("roll") roll: String,
        @Query("userId") userId: String,
        @Query("roomId") roomId : Long
    ) : Flowable<DashBoardResponse>
}