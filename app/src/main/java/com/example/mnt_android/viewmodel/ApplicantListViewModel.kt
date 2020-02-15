package com.example.mnt_android.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mnt_android.base.BaseViewModel
import com.example.mnt_android.service.DBApi
import com.example.mnt_android.service.model.Applicant
import com.example.mnt_android.service.model.User
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class ApplicantListViewModel(private val dbApi: DBApi) : BaseViewModel() {
    var roomId = MutableLiveData<Int>()
    var isManager = MutableLiveData<Int>()
    private var _applicantList = MutableLiveData<ArrayList<User>>()
    val applicantList : LiveData<ArrayList<User>>
        get() = _applicantList

    fun setApplicantList() {
        addDisposable(
            dbApi.userList(roomId.value ?: -1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    it.run {
                        if (apiStatus.httpStatus == 200) {
                            val userList = arrayListOf<User>()
                            data.forEach { applicant ->
                                userList.add(applicant.user)
                            }
                            _applicantList.value = userList
                        }
                    }
                }, {

                })
        )
    }
}