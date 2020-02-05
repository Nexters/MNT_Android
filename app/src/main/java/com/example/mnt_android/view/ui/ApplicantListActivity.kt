package com.example.mnt_android.view.ui

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mnt_android.R
import com.example.mnt_android.view.adapter.ApplicantListAdapter
import com.example.mnt_android.base.BaseActivity
import com.example.mnt_android.base.BaseViewModel
import com.example.mnt_android.databinding.ActivityApplicantListBinding
import kotlinx.android.synthetic.main.activity_applicant_list.*

class ApplicantListActivity : BaseActivity<ActivityApplicantListBinding, BaseViewModel>() {
    override lateinit var viewModel : BaseViewModel
    override val layoutId: Int
        get() = R.layout.activity_applicant_list

    override fun initSetting() {
        viewModel = BaseViewModel()
        applicant_rv.apply {
            layoutManager = LinearLayoutManager(this@ApplicantListActivity)
            adapter = ApplicantListAdapter()
        }

        close_btn.setOnClickListener {
            finish()
        }
    }
}
