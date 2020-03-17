package com.example.mnt_android.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mnt_android.base.BaseViewModel
import com.example.mnt_android.service.model.*
import com.example.mnt_android.service.repository.DBRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class GameViewModel : BaseViewModel()
{
    var title : String=""
    var userMissions = ArrayList<UserMissionResponse>()
    var missionResponses = ArrayList<MissionResponse>()
    var missionManager = ArrayList<Pair<String,String>>()
    var changeManagerList = MutableLiveData<Boolean>()
    private val repository = DBRepository()


    fun getUserMission(userId : String, roomId : Long)
    {
        addDisposable(
            repository.getUserMission(userId,roomId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({t ->
                    userMissions = ArrayList(t.userMissionResponses)// 나의 미션 정보
                    Log.d("wlgusdnzzz",userMissions.size.toString())
                    Log.d("wlgusdnzzz",userMissions[0].missionName)
                },{t ->
                    Log.d("wlgusdnzzz",t.toString())
                })

        )
    }

    fun groupByMission(roomId : Long)
    {
        changeManagerList.value=false
              missionManager.clear()
        addDisposable(repository.groupByMission(roomId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({t ->
                Log.d("wlgusdnzzz",t.missionResponses.size.toString())
                missionResponses = ArrayList(t.missionResponses)
                for(data in missionResponses)
                {
                    Log.d("wlgusdnzzz",data.toString())
                    Log.d("wlgusdnzzz",data.userMissions.size.toString())
                    var total = data.userMissions.size
                    var done = 0
                    for(userData in data.userMissions)
                    {
                        if(userData.userMission.userDone==1)
                            done++
                    }
                    missionManager.add(Pair(data.missionName,"$done/$total"))
                }
                changeManagerList.value=true
            },
                {

                }))
    }

}