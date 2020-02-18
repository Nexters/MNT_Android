package com.example.mnt_android.view.ui

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mnt_android.R
import com.example.mnt_android.view.adapter.ApplicantListAdapter
import com.example.mnt_android.base.BaseActivity
import com.example.mnt_android.databinding.ActivityApplicantListBinding
import com.example.mnt_android.util.TAG_IS_MANAGER
import com.example.mnt_android.util.TAG_ROOM_ID
import com.example.mnt_android.viewmodel.ApplicantListViewModel
import kotlinx.android.synthetic.main.activity_applicant_list.*
import org.koin.android.viewmodel.ext.android.viewModel

class ApplicantListActivity :
    BaseActivity<ActivityApplicantListBinding, ApplicantListViewModel>() {
    override val viewModel by viewModel<ApplicantListViewModel>()
    override val layoutId: Int
        get() = R.layout.activity_applicant_list

    override fun initSetting() {
        applicant_rv.apply {
            layoutManager = LinearLayoutManager(this@ApplicantListActivity)
            adapter = ApplicantListAdapter { roomId, userId ->
                viewModel.exitApplicant(roomId, userId)
            }
        }

        setViewModel()
        setEventListener()
    }

    private fun setViewModel() {
        dataBinding.viewModel = viewModel
        dataBinding.lifecycleOwner = this
        viewModel.run {
            roomId.value = intent.getLongExtra(TAG_ROOM_ID, -1)
            isManager.value = intent.getIntExtra(TAG_IS_MANAGER, 0)
            setApplicantList()
        }
    }

    private fun setEventListener() {
        close_btn.setOnClickListener {
            finish()
        }
    }
}
