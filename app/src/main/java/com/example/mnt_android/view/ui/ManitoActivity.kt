package com.example.mnt_android.view.ui

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mnt_android.R
import com.example.mnt_android.base.BaseActivity
import com.example.mnt_android.base.BaseViewModel
import com.example.mnt_android.databinding.ActivityManitoBinding
import com.example.mnt_android.view.adapter.ManitoListAdapter
import kotlinx.android.synthetic.main.activity_manito.*

class ManitoActivity : BaseActivity<ActivityManitoBinding, BaseViewModel>() {
    override var viewModel = BaseViewModel()
    override val layoutId: Int
        get() = R.layout.activity_manito

    override fun initSetting() {
        val isManager = intent.getBooleanExtra(GameActivity.TAG_IS_MANAGER, false)
        member_list_rv.apply {
            layoutManager = LinearLayoutManager(this@ManitoActivity)
            adapter = ManitoListAdapter(isManager)
        }
        close_btn.setOnClickListener { finish() }
    }
}
