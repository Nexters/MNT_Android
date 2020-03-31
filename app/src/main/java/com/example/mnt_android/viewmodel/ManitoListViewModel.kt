package com.example.mnt_android.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mnt_android.base.BaseViewModel
import com.example.mnt_android.extension.isFalse
import com.example.mnt_android.service.model.Applicant
import com.example.mnt_android.service.repository.DBRepository
import com.example.mnt_android.service.repository.PreferencesRepository
import com.example.mnt_android.util.SUCCESS
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ManitoListViewModel(
    private val dbRepository: DBRepository,
    private val pr: PreferencesRepository
) : BaseViewModel() {
    private val _manitoList = MutableLiveData<ArrayList<Applicant>>()
    val manitoList: LiveData<ArrayList<Applicant>>
        get() = _manitoList

    fun getUserList() {
        addDisposable(
            dbRepository.userList(pr.getRoomId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    it.run {
                        if (apiStatus.httpStatus == SUCCESS) {
                            _manitoList.value = data
                        }
                    }
                }, {

                })
        )
    }

    fun setCheckNaeto() = pr.setCheckNaeto()

    fun getIsManager() = pr.getIsManager()
    fun getUserId() = pr.getUserId()
}