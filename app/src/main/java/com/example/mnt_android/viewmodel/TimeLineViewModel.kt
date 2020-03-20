package com.example.mnt_android.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mnt_android.base.BaseViewModel
import com.example.mnt_android.service.model.UserMissionResponse
import com.example.mnt_android.service.repository.DBRepository
import com.example.mnt_android.util.SUCCESS
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class TimeLineViewModel(private val dbRepository: DBRepository) : BaseViewModel(){
    val isManager = MutableLiveData<Boolean>(false)
    private val _contentList = MutableLiveData<ArrayList<UserMissionResponse>>()
    val contentList: LiveData<ArrayList<UserMissionResponse>> = _contentList

    fun setContentList(roomId: Long) {
        addDisposable(
            dbRepository.getMissionList(roomId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    it.run {
                        if(apiStatus.httpStatus == SUCCESS) {
                            _contentList.value = data
                        }
                    }
                }, {})
        )
    }
}