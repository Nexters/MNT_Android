package com.example.mnt_android.view.ui

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mnt_android.R
import com.example.mnt_android.base.BaseActivity
import com.example.mnt_android.databinding.ActivitySelectManitoBinding
import com.example.mnt_android.view.adapter.SelectManitoListAdapter
import com.example.mnt_android.viewmodel.ManitoListViewModel
import kotlinx.android.synthetic.main.activity_select_manito.*
import org.koin.android.viewmodel.ext.android.viewModel

class SelectManitoActivity : BaseActivity<ActivitySelectManitoBinding, ManitoListViewModel>() {
    override val viewModel by viewModel<ManitoListViewModel>()
    override val layoutId: Int
        get() = R.layout.activity_select_manito

    override fun initSetting() {
        setDatabinding()
        setEventListener()
    }

    private fun setDatabinding() {
        val sf = getSharedPreferences("login", 0)
        val roomId = sf.getLong("roomId", 0)

        dataBinding.lifecycleOwner = this
        dataBinding.viewModel = viewModel.apply {
            getUserList(roomId)
        }
    }

    private fun setEventListener() {
        participants_rv.run {
            layoutManager = GridLayoutManager(context, 2)
            adapter = SelectManitoListAdapter().apply {
                item.observe(this@SelectManitoActivity, Observer {
                    confirm_btn.isEnabled = true
                })
            }
        }
        }
    }
}
