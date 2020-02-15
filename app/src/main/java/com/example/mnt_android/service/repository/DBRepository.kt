package com.example.mnt_android.service.repository

import com.example.mnt_android.service.ApiManager
import com.example.mnt_android.service.model.CheckRoom
import com.example.mnt_android.service.model.CheckRoomList
import com.example.mnt_android.service.model.Room
import com.example.mnt_android.service.model.User
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

   fun createRoom(endDay : String,
                  id : Int,
                  isDone : Int,
                  isStart : Int,
                  maxPeople : Int,
                  name : String,
                  startDay : String) : Completable =
       api.createRoom(endDay,id,isDone,isStart,maxPeople,name,startDay)

    fun attendRoom(roomId : Int,
                   userId : String) : Completable =
        api.attendRoom(roomId,userId)







}