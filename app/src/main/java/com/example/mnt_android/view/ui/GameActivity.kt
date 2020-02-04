package com.example.mnt_android.view.ui

import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.mnt_android.R
import com.example.mnt_android.base.BaseActivity
import com.example.mnt_android.base.BaseViewModel
import com.example.mnt_android.databinding.ActivityGameBinding
import com.example.mnt_android.service.model.DoMission
import kotlinx.android.synthetic.main.activity_game.*

class GameActivity : BaseActivity<ActivityGameBinding, BaseViewModel>(), View.OnClickListener {

    companion object {
        const val TAG_IS_MANAGER = "isManager"
    }

    private lateinit var fragmentManager: FragmentManager
    private lateinit var fragmentTransaction: FragmentTransaction

    private lateinit var timeLineFragment: TimeLineFragment
    private lateinit var missionFragment: GameMissionFragment
    private lateinit var dashBoardFragment: DashBoardFragment

    override var viewModel = BaseViewModel()
    override val layoutId: Int
        get() = R.layout.activity_game

    override fun initSetting() {
        val isManager = intent.getBooleanExtra(TAG_IS_MANAGER, false)

        timeLineFragment = TimeLineFragment(isManager)
        missionFragment = GameMissionFragment()
        dashBoardFragment = DashBoardFragment(isManager)

        fragmentManager = supportFragmentManager

        changeFragment(timeLineFragment)

        Toast.makeText(this, "Game", Toast.LENGTH_SHORT).show()
    }

    override fun onClick(v: View?) {
        when (v) {
            dashboard_tv -> changeFragment(dashBoardFragment)
            show_all_tv -> changeFragment(timeLineFragment)
            mission_tv -> changeFragment(missionFragment)
        }
    }

    private fun changeFragment(fragment: Fragment) {
        fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frag_game, fragment)
        fragmentTransaction.commit()
    }

    fun startMission() {
        val mission = DoMission("미션이름", "미션설명", "")
        Toast.makeText(this, "미션수행", Toast.LENGTH_SHORT).show()
        val intent = Intent(this@GameActivity, DoMissionActivity::class.java)
        intent.putExtra("mission", mission)
        startActivity(intent)
    }
    fun createMission()
    {

        val intent = Intent(this@GameActivity,CreateMissionActivity::class.java)
        startActivity(intent)
    }

}
