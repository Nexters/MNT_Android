package com.example.mnt_android.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mnt_android.base.BaseViewModel
import com.example.mnt_android.service.model.DashBoard
import com.example.mnt_android.service.repository.DBRepository
import com.example.mnt_android.util.SUCCESS
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DashBoardViewModel(private val dbRepository: DBRepository, private val _manittoName: String?) : BaseViewModel() {
    val manittoName: LiveData<String>
        get() = MutableLiveData(_manittoName)
    private val _dashBoard = MutableLiveData<DashBoard>()
    val dashBoard: LiveData<DashBoard> = _dashBoard

    fun loadDashBoard(roll: String, userId: String, roomId: Long) {
        addDisposable(
            dbRepository.getDashBoardData(roll, roomId, userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    it.run {
                        if (apiStatus.httpStatus == SUCCESS) {
                            _dashBoard.value = this.data
                        }
                    }
                }, {})
        )
    }

    fun endRoom(roomId: Long) {
        addDisposable(
            dbRepository.endRoom(roomId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({

                }, {})
        )
    }
}