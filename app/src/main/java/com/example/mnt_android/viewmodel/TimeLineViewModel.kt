package com.example.mnt_android.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mnt_android.base.BaseViewModel
import com.example.mnt_android.service.model.UserMissionResponse
import com.example.mnt_android.service.repository.DBRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class TimeLineViewModel(private val dbRepository: DBRepository) : BaseViewModel(){
    val isManager = MutableLiveData<Boolean>(false)
    private val _contentList = MutableLiveData<UserMissionResponse>()
    val contentList: LiveData<UserMissionResponse>
        get() = _contentList

    fun setContentList(roomId: Long) {
        addDisposable(
            dbRepository.getMissionList(roomId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _contentList.value = it
                }, {})
        )
    }
}