package com.example.mnt_android.view.ui

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mnt_android.R
import com.example.mnt_android.base.BaseActivity
import com.example.mnt_android.databinding.ActivityManitoBinding
import com.example.mnt_android.util.TAG_IS_MANAGER
import com.example.mnt_android.util.TAG_ROOM_ID
import com.example.mnt_android.view.adapter.ManitoListAdapter
import com.example.mnt_android.viewmodel.ManitoListViewModel
import kotlinx.android.synthetic.main.activity_manito.*
import org.koin.android.viewmodel.ext.android.viewModel

class ManitoActivity : BaseActivity<ActivityManitoBinding, ManitoListViewModel>() {
    override val viewModel by viewModel<ManitoListViewModel>()
    override val layoutId: Int
        get() = R.layout.activity_manito

    override fun initSetting() {
        member_list_rv.apply {
            layoutManager = LinearLayoutManager(this@ManitoActivity)
            adapter = ManitoListAdapter()
        }

        setViewModel()
        setEventListener()
    }

    private fun setViewModel() {
        dataBinding.lifecycleOwner = this
        dataBinding.viewModel = viewModel.apply {
            getUserList()
        }
    }

    private fun setEventListener() {
        close_btn.setOnClickListener { finish() }
    }
}
