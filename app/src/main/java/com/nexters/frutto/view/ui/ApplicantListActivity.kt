package com.nexters.frutto.view.ui

import androidx.recyclerview.widget.LinearLayoutManager
import com.nexters.frutto.R
import com.nexters.frutto.view.adapter.ApplicantListAdapter
import com.nexters.frutto.base.BaseActivity
import com.nexters.frutto.databinding.ActivityApplicantListBinding
import com.nexters.frutto.util.TAG_IS_MANAGER
import com.nexters.frutto.util.TAG_ROOM_ID
import com.nexters.frutto.viewmodel.ApplicantListViewModel
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
