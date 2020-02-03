package com.example.mnt_android.view.ui

import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.mnt_android.R
import com.example.mnt_android.base.BaseActivity
import com.example.mnt_android.base.BaseViewModel
import com.example.mnt_android.databinding.ActivityGameBinding
import com.example.mnt_android.service.model.DoMission
import kotlinx.android.synthetic.main.activity_game.*

class GameActivity : BaseActivity<ActivityGameBinding, BaseViewModel>(), View.OnClickListener {
    lateinit var fragmentManager: FragmentManager
    lateinit var fragmentTransaction : FragmentTransaction
    lateinit var timeLineFragment: TimeLineFragment
    lateinit var missionFragment: GameMissionFragment


    override var viewModel = BaseViewModel()
    override val layoutId: Int
        get() = R.layout.activity_game

    override fun initSetting() {
        fragmentManager = supportFragmentManager
        fragmentTransaction = fragmentManager.beginTransaction()
        timeLineFragment = TimeLineFragment()
        missionFragment = GameMissionFragment()
        dataBinding.gameActivity=this


        Toast.makeText(this,"Game",Toast.LENGTH_SHORT).show()

        setFrag(2)

    }

    fun setFrag(n : Int)
    {
        fragmentTransaction = fragmentManager.beginTransaction()

        when(n)
        {
            0 ->
            {
               //대시보드

            }
            1->
            {
                fragmentTransaction.replace(R.id.frag_game,timeLineFragment)

                fragmentTransaction.commit()
            }
            2->
            {
                fragmentTransaction.replace(R.id.frag_game,missionFragment)

                fragmentTransaction.commit()
            }
        }
    }

    override fun onClick(v: View?) {
        // 각 버튼별 onclick 구현은 여기서 해주세요!

        Toast.makeText(this,"미션클릭",Toast.LENGTH_SHORT).show()
        when (v) {
            dashboard_tv -> {

            }

            show_all_tv -> {

                setFrag(1)
            }

           mission_tv -> {
                setFrag(2)

            }
        }
    }

    fun startMission()
    {
        val mission = DoMission("미션이름","미션설명","")
        Toast.makeText(this,"미션수행",Toast.LENGTH_SHORT).show()
        val intent = Intent(this@GameActivity,DoMissionActivity::class.java)
        intent.putExtra("mission",mission)
        startActivity(intent)
    }

}
