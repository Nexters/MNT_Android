package com.example.mnt_android.view.ui

import android.widget.ImageView
import com.example.mnt_android.R
import com.example.mnt_android.base.BaseActivity
import com.example.mnt_android.databinding.ActivityMissionDetailBinding
import com.example.mnt_android.util.TAG_IS_MANAGER
import com.example.mnt_android.util.getFruttoData
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
            isManager = intent.getBooleanExtra(TAG_IS_MANAGER, false)
            setMission(intent.getParcelableExtra(TAG_MISSION))
        }

        setFruitImg(viewModel.mission.value?.userFruttoId, dataBinding.naetoIv)
        setFaceImg(viewModel.mission.value?.manitto?.fruttoId, dataBinding.nitoIv)
    }

    private fun setEventListener() {
        close_btn.setOnClickListener { finish() }
    }

    private fun setFruitImg(imgId: Int?, iv: ImageView) {
        val imgNm = getFruttoData(baseContext, (imgId ?: -1) + 1)?.englishName
        val imgRes = resources.getIdentifier(
            "img_profile_chat_${imgNm}",
            "drawable",
            packageName
        )
        iv.setImageResource(imgRes)
    }

    private fun setFaceImg(imgId: Int?, iv: ImageView) {
        val imgRes = resources.getIdentifier(
            "img_profile_face_${"%02d".format(imgId)}",
            "drawable",
            packageName
        )
        iv.setImageResource(imgRes)
    }
}
