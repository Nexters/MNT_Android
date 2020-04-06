package com.example.mnt_android.view.ui

import android.content.Intent
import com.example.mnt_android.R
import com.example.mnt_android.base.BaseActivity
import com.example.mnt_android.base.BaseViewModel
import com.example.mnt_android.binding.setFruitProfileSrc
import com.example.mnt_android.databinding.ActivityShowManittoBinding
import com.example.mnt_android.extension.noticeDate
import com.example.mnt_android.viewmodel.ShowManittoViewModel
import kotlinx.android.synthetic.main.activity_show_manitto.*
import org.koin.android.viewmodel.ext.android.viewModel

class ShowManittoActivity : BaseActivity<ActivityShowManittoBinding, BaseViewModel>() {
    companion object {
        const val TAG_END_DAY = "endDay"
    }

    override val viewModel by viewModel<ShowManittoViewModel>()
    override val layoutId: Int
        get() = R.layout.activity_show_manitto

    override fun initSetting() {
        setDatabinding()
        setEventListener()
    }

    private fun setDatabinding() {
        dataBinding.lifecycleOwner = this
        dataBinding.viewModel = viewModel.apply {
            loadUserList()
            loadManittoData()
        }
        dataBinding.run {
            val noticeEndDay =
                "${intent.getStringExtra(TAG_END_DAY).noticeDate} ${resources.getString(R.string.tv_notice_end_day)}"
            naetoTv1.text = viewModel?.getUserNm()
            naetoTv2.text = viewModel?.getUserNm()
            setFruitProfileSrc(naetoIv, viewModel?.getUserFruttoId())
            noticeEndDayTv.text = noticeEndDay
        }
    }

    private fun setEventListener() {
        confirm_btn.setOnClickListener {
            viewModel.setCheckNito()
            val i = Intent(this, GameActivity::class.java)
            startActivity(i)
            finish()
        }
    }
}
