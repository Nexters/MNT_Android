package com.example.mnt_android.view.ui

import com.example.mnt_android.R
import com.example.mnt_android.base.BaseActivity
import com.example.mnt_android.base.BaseViewModel
import com.example.mnt_android.databinding.ActivityShowManittoBinding

class ShowManittoActivity : BaseActivity<ActivityShowManittoBinding, BaseViewModel>() {
    override val viewModel = BaseViewModel()
    override val layoutId: Int
        get() = R.layout.activity_show_manitto

    override fun initSetting() {

    }
}
