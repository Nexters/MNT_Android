package com.nexters.frutto.view.ui

import com.nexters.frutto.R
import com.nexters.frutto.base.BaseActivity
import com.nexters.frutto.databinding.ActivityMissionDetailBinding
import com.nexters.frutto.service.KakaoMessageService
import com.nexters.frutto.viewmodel.MissionDetailViewModel
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
            setMission(intent.getParcelableExtra(TAG_MISSION))
        }
    }

    private fun setEventListener() {
        close_btn.setOnClickListener { finish() }
        share_btn.setOnClickListener {
            viewModel.mission.value?.let {
                KakaoMessageService.shareMission(baseContext, it)
            }
        }
    }
}
