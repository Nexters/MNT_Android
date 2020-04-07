package com.example.mnt_android.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mnt_android.base.BaseViewModel
import com.example.mnt_android.extension.isTrue
import com.example.mnt_android.service.model.*
import com.example.mnt_android.service.repository.DBRepository
import com.example.mnt_android.util.SUCCESS
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class GameViewModel(private val repository: DBRepository) : BaseViewModel()
{
    var title : String=""
    var doneUserMissions = ArrayList<UserMissionResponse>()
    var notDoneUserMissions = ArrayList<UserMissionResponse>()
    var missionResponses = ArrayList<MissionResponse>()
    var missionManager = ArrayList<Pair<String,String>>()
    var changeManagerList = MutableLiveData<Boolean>()
    var listIsDone = MutableLiveData<Boolean>(false)

    fun getUserMission(userId : String, roomId : Long)
    {
        listIsDone.value=false
        addDisposable(
            repository.getUserMission(userId,roomId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    it.run {
                        if(apiStatus.httpStatus == SUCCESS)
                        {
                            Log.d("wlgusdnzzz","미션 Size : "+this.data.size.toString())
                            val done = arrayListOf<UserMissionResponse>()
                            val notDone = arrayListOf<UserMissionResponse>()

                            this.data.forEach { mission ->

                                mission.userMission.userDone?.let { isDone ->
                                    if (isDone.isTrue) done.add(mission)
                                } ?: { notDone.add(mission) }()

                                doneUserMissions = done
                                notDoneUserMissions = notDone
                            }

                        }
                        listIsDone.value=true
                    }
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
                missionResponses = ArrayList(t.missionResponses)
                for(data in missionResponses)
                {
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