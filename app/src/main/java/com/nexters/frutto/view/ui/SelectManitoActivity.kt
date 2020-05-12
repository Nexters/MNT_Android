package com.nexters.frutto.view.ui

import android.content.Intent
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.nexters.frutto.R
import com.nexters.frutto.base.BaseActivity
import com.nexters.frutto.databinding.ActivitySelectManitoBinding
import com.nexters.frutto.view.adapter.SelectManitoListAdapter
import com.nexters.frutto.view.dialog.NoticeManitoDialog
import com.nexters.frutto.viewmodel.ManitoListViewModel
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
        dataBinding.lifecycleOwner = this
        dataBinding.viewModel = viewModel.apply {
            getLocalUserList()
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
            var naetoNm: String? = null
            var naetoFruttoId: Int? = null
            viewModel.manitoList.value?.forEach checkNaeto@ { applicant ->
                if(applicant.manitto.id == viewModel.getUserId()) {
                    naetoNm = applicant.user.name
                    naetoFruttoId = applicant.userFruttoId
                    return@checkNaeto
                }
            }

            NoticeManitoDialog(naetoFruttoId, naetoNm) {
                viewModel.setCheckNaeto()
                val i = Intent(baseContext, GameActivity::class.java)
                startActivity(i)
                finish()
            }.show(supportFragmentManager, TAG)
        }
    }
}
