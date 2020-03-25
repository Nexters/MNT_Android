package com.example.mnt_android.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mnt_android.base.BaseViewModel
import com.example.mnt_android.extension.isFalse
import com.example.mnt_android.service.model.Applicant
import com.example.mnt_android.service.repository.DBRepository
import com.example.mnt_android.util.SUCCESS
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ManitoListViewModel(private val dbRepository: DBRepository) : BaseViewModel() {
    private val _manitoList = MutableLiveData<ArrayList<Applicant>>()
    val isManager = MutableLiveData<Boolean>()

    val manitoList: LiveData<ArrayList<Applicant>>
        get() = _manitoList

    fun getUserList(roomId: Long) {
        addDisposable(
            dbRepository.userList(roomId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    it.run {
                        if (apiStatus.httpStatus == SUCCESS) {
                            val userList = arrayListOf<Applicant>()
                            data.forEach { applicant ->
                                if(applicant.isCreater.isFalse) userList.add(applicant)
                            }
                            _manitoList.value = userList
                        }
                    }
                }, {

                })
        )
    }
}