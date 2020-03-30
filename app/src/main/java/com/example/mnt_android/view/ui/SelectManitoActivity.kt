package com.example.mnt_android.view.ui

import android.content.Intent
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mnt_android.R
import com.example.mnt_android.base.BaseActivity
import com.example.mnt_android.databinding.ActivitySelectManitoBinding
import com.example.mnt_android.service.model.UserResponse
import com.example.mnt_android.view.adapter.SelectManitoListAdapter
import com.example.mnt_android.view.dialog.NoticeManitoDialog
import com.example.mnt_android.viewmodel.ManitoListViewModel
import kotlinx.android.synthetic.main.activity_select_manito.*
import org.jetbrains.anko.sdk21.listeners.onClick
import org.koin.android.viewmodel.ext.android.viewModel

class SelectManitoActivity : BaseActivity<ActivitySelectManitoBinding, ManitoListViewModel>() {
    companion object {
        private const val TAG = "Popup End Dialog"
    }

    override val viewModel by viewModel<ManitoListViewModel>()
    override val layoutId: Int
        get() = R.layout.activity_select_manito

    override fun initSetting() {
        setDatabinding()
        setEventListener()
    }

    private fun setDatabinding() {
        val sf = getSharedPreferences("login", 0)
        val roomId = sf.getLong("roomId", 0)

        dataBinding.lifecycleOwner = this
        dataBinding.viewModel = viewModel.apply {
            getUserList(roomId)
        }
    }

    private fun setEventListener() {
        participants_rv.run {
            layoutManager = GridLayoutManager(context, 2)
            adapter = SelectManitoListAdapter().apply {
                item.observe(this@SelectManitoActivity, Observer {
                    confirm_btn.isEnabled = true
                })
            }
        }
        confirm_btn.onClick {
            var manito: UserResponse? = null
            val sf = getSharedPreferences("login", 0)
            val userId = sf.getString("kakao_token", "")
            viewModel.manitoList.value?.forEach checkNaeto@ { applicant ->
                if(applicant.user.id == userId) {
                    manito = applicant.manitto
                    return@checkNaeto
                }
            }

            NoticeManitoDialog(manito?.fruttoId, manito?.name) {
                // TODO : 방이 종료되었다고 설정하는 코드 필요
                val i = Intent(baseContext, GameActivity::class.java)
                startActivity(i)
                finish()
            }.show(supportFragmentManager, TAG)
        }
    }
}
