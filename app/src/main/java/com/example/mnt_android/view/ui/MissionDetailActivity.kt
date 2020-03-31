package com.example.mnt_android.view.ui

import com.example.mnt_android.R
import com.example.mnt_android.base.BaseActivity
import com.example.mnt_android.databinding.ActivityMissionDetailBinding
import com.example.mnt_android.util.TAG_IS_MANAGER
import com.example.mnt_android.viewmodel.MissionDetailViewModel
import kotlinx.android.synthetic.main.activity_mission_detail.*
import org.koin.android.viewmodel.ext.android.viewModel

class MissionDetailActivity : BaseActivity<ActivityMissionDetailBinding, MissionDetailViewModel>() {
    companion object {
        const val TAG_MISSION = "Mission"
    }

    override val viewModel by viewModel<MissionDetailViewModel>()
    override val layoutId: Int
        get() = R.layout.activity_mission_detail

    override fun initSetting() {
        setViewModel()
        setEventListener()
    }

    private fun setViewModel() {
        dataBinding.lifecycleOwner = this
        dataBinding.viewModel = viewModel.apply {
            isManager.value = intent.getBooleanExtra(TAG_IS_MANAGER, false)
            setMission(intent.getParcelableExtra(TAG_MISSION))
        }
    }

    private fun setEventListener() {
        close_btn.setOnClickListener { finish() }
    }
}
