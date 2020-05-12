package com.nexters.frutto.view.ui

import android.content.Intent
import com.nexters.frutto.R
import com.nexters.frutto.base.BaseActivity
import com.nexters.frutto.base.BaseViewModel
import com.nexters.frutto.binding.setFruitProfileSrc
import com.nexters.frutto.databinding.ActivityShowManittoBinding
import com.nexters.frutto.extension.noticeDate
import com.nexters.frutto.viewmodel.ShowManittoViewModel
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
