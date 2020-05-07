package com.nexters.frutto.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nexters.frutto.base.BaseViewModel
import com.nexters.frutto.service.model.Applicant
import com.nexters.frutto.service.repository.DBRepository
import com.nexters.frutto.util.SUCCESS
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class ApplicantListViewModel(private val dbRepository: DBRepository) : BaseViewModel() {
    var roomId = MutableLiveData<Long>()
    var isManager = MutableLiveData<Int>()
    private var _applicantList = MutableLiveData<ArrayList<Applicant>>()
    val applicantList : LiveData<ArrayList<Applicant>>
        get() = _applicantList

    fun setApplicantList() {
        addDisposable(
            dbRepository.userList(roomId.value ?: -1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    it.run {
                        if (apiStatus.httpStatus == SUCCESS) {
                            val userList = arrayListOf<Applicant>()
                            data.forEach { applicant ->
                                userList.add(applicant)
                            }
                            _applicantList.value = userList
                        }
                    }
                }, {

                })
        )
    }

    fun exitApplicant(roomId: Long, userId: String) {
        addDisposable(
            dbRepository.exitUser(roomId, userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it.apiStatus.httpStatus == SUCCESS) {
                        setApplicantList()
                    }
                }, {

                })
        )
    }
}