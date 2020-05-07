package com.nexters.frutto.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nexters.frutto.base.BaseViewModel
import com.nexters.frutto.service.model.Applicant
import com.nexters.frutto.service.repository.DBRepository
import com.nexters.frutto.service.repository.PreferencesRepository
import com.nexters.frutto.util.SUCCESS
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
        if(!getCheckNaeto()) {
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
        } else {
            getLocalUserList()
        }
    }

    fun getLocalUserList() { _manitoList.value = pr.getUserList()}

    fun setCheckNaeto() = pr.setCheckNaeto()

    fun getIsManager() = pr.getIsManager()
    fun getUserId() = pr.getUserId()
    fun getCheckNaeto() = pr.getCheckNaeto()
}