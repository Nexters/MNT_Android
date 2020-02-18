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

    fun userList(roomId : Long) : Single<ApplicantResponse> =
        api.userList(roomId)

    fun startRoom(roomId : Long) : Completable =
        api.startRoom(roomId)

    fun exitUser(roomId: Long, userId: String): Completable = api.exitUser(roomId, userId)

    fun makeMission(makemission : MakeMission) : Completable =
        api.makeMission(makemission)



}