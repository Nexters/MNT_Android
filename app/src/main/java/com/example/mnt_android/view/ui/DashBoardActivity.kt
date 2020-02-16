package com.example.mnt_android.view.ui

import com.example.mnt_android.R
import com.example.mnt_android.base.BaseActivity
import com.example.mnt_android.base.BaseViewModel
import com.example.mnt_android.databinding.ActivityDashBoardBinding
import com.example.mnt_android.util.FALSE
import com.example.mnt_android.util.TAG_IS_MANAGER

class DashBoardActivity : BaseActivity<ActivityDashBoardBinding, BaseViewModel>() {
    override val viewModel = BaseViewModel()
    override val layoutId: Int
        get() = R.layout.activity_dash_board

    override fun initSetting() {
        val isManager = intent.getIntExtra(TAG_IS_MANAGER, FALSE) == 1
    }
}
