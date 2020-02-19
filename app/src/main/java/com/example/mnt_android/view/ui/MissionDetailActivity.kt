package com.example.mnt_android.view.ui

import com.example.mnt_android.R
import com.example.mnt_android.base.BaseActivity
import com.example.mnt_android.base.BaseViewModel
import com.example.mnt_android.databinding.ActivityMissionDetailBinding
import kotlinx.android.synthetic.main.activity_mission_detail.*

class MissionDetailActivity : BaseActivity<ActivityMissionDetailBinding, BaseViewModel>() {
    override val viewModel = BaseViewModel()
    override val layoutId: Int
        get() = R.layout.activity_mission_detail

    override fun initSetting() {
        setEventListener()
    }

    private fun setEventListener() {
        close_btn.setOnClickListener { finish() }
    }
}
