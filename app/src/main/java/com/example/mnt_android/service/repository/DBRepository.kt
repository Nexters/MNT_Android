package com.example.mnt_android.service.repository

import com.example.mnt_android.service.ApiManager
import com.example.mnt_android.service.model.*
import com.google.gson.JsonObject
import com.kakao.usermgmt.StringSet.name
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import okhttp3.MultipartBody
import org.intellij.lang.annotations.Flow
import retrofit2.http.Multipart
import java.io.File

class DBRepository {

    private val api = ApiManager.dbApi


//cmToken : String, id : String, name : String, profilePic : String
    fun signUp(user : User) : Completable =
        api.signUp(user)
            .subscribeOn(Schedulers.io())

    fun checkRoom(userId : String) : Flowable<CheckRoomList> =
        api.checkRoom(userId)

   fun createRoom(room : SendRoom,
                  userId : String) : Flowable<RoomId> =
       api.createRoom(room,userId)

    fun attendRoom(roomId : Long,
                   userId : String) : Completable =
        api.attendRoom(roomId,userId)

    fun endRoom(roomId: Long): Completable =
        api.endRoom(roomId)

    fun deleteRoom(roomId: Long) = api.deleteRoom(roomId)

    fun getManitto(
        roomId: Long,
        userId: String
    ): Flowable<UserResponseResult> =
        api.getManitto(roomId, userId)

    fun userList(roomId : Long) : Single<ApplicantResponse> =
        api.userList(roomId)

    fun startRoom(roomId : Long) : Single<ApiResponse> =
        api.startRoom(roomId)

    fun exitUser(roomId: Long, userId: String) = api.exitUser(roomId, userId)

    fun makeMission(mission : Mission) : Completable =
        api.makeMission(mission)

    fun sendMission(
        roomId: Long,
        userId: String,
        missionId: Long,
        content: String,
        img: MultipartBody.Part?
    ) = when (img == null) {
        true -> api.sendMission(roomId, userId, missionId, content)
        false -> api.sendMission(roomId, userId, missionId, content, img)
    }
    
    fun getUserMission(userId : String,
                       roomId : Long) : Flowable<MissionListResponse> =
        api.getUserMission(userId,roomId)

    fun groupByMission(roomId : Long) : Flowable<GetManagerMission> =
        api.groupByMission(roomId)

    fun getMissionList(roomId: Long): Flowable<MissionListResponse> = api.getMissionList(roomId)

    fun getDashBoardData(roll: String, roomId: Long, userId: String) = api.getDashBoardData(roll, userId, roomId)
}