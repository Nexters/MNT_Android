package com.nexters.frutto.view.ui

import androidx.recyclerview.widget.LinearLayoutManager
import com.nexters.frutto.R
import com.nexters.frutto.base.BaseActivity
import com.nexters.frutto.databinding.ActivityManitoBinding
import com.nexters.frutto.view.adapter.ManitoListAdapter
import com.nexters.frutto.viewmodel.ManitoListViewModel
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
