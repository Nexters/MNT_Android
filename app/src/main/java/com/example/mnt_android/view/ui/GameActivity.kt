package com.example.mnt_android.view.ui

import android.view.View
import com.example.mnt_android.R
import com.example.mnt_android.base.BaseActivity
import com.example.mnt_android.base.BaseViewModel
import com.example.mnt_android.databinding.ActivityGameBinding
import kotlinx.android.synthetic.main.activity_game.*

class GameActivity : BaseActivity<ActivityGameBinding, BaseViewModel>(), View.OnClickListener {
    override var viewModel = BaseViewModel()
    override val layoutId: Int
        get() = R.layout.activity_game

    override fun initSetting() {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        val fragment = TimeLineFragment()

        fragmentTransaction.add(framelayout.id, fragment)
        fragmentTransaction.commit()
    }

    override fun onClick(v: View?) {
        // 각 버튼별 onclick 구현은 여기서 해주세요!
        when (v) {
            dashboard_tv -> {

            }

            show_all_tv -> {

            }

            mission_tv -> {

            }
        }
    }

}
