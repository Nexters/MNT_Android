package com.example.mnt_android.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mnt_android.base.BaseViewModel
import com.example.mnt_android.service.model.UserResponse
import com.example.mnt_android.service.repository.DBRepository
import com.example.mnt_android.service.repository.PreferencesRepository
import com.example.mnt_android.util.SUCCESS
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ShowManittoViewModel(
    private val dbRepository: DBRepository,
    private val pr: PreferencesRepository
) : BaseViewModel() {
    private val _manittoData = MutableLiveData<UserResponse>()
    val manittoData: LiveData<UserResponse> = _manittoData

    fun loadManittoData() {
        addDisposable(
            dbRepository.getManitto(pr.getRoomId(), pr.getUserId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    it.run {
                        if (apiStatus.httpStatus == SUCCESS) {
                            _manittoData.value = data
                        }
                    }
                }, {})
        )
    }

    fun loadUserList() {
        addDisposable(
            dbRepository.userList(pr.getRoomId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    it.run {
                        if (apiStatus.httpStatus == SUCCESS) {
                            pr.setUserList(data)
                        }
                    }
                }, {

                })
        )
    }

    fun setCheckNito() = pr.setCheckNito()

    fun getUserNm() = pr.getUserNm()
    fun getUserFruttoId() = pr.getFruttoId()
}