package com.example.mnt_android.service.repository

import com.example.mnt_android.service.ApiManager
import com.example.mnt_android.service.model.CheckRoom
import com.example.mnt_android.service.model.Room
import com.kakao.usermgmt.response.model.User
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single

class DBRepository {

    private val api = ApiManager.dbApi



    fun signUp(id : Int,
               name : String,
               profilePic : String,
               fcmToken : String) : Completable =
        api.signUp(id,name,profilePic,fcmToken)

    fun checkRoom(userId : Int) : Single<CheckRoom> =
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
                   userId : Int) : Single<Room> =
        api.attendRoom(roomId,userId)







}