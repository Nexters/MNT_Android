package com.example.mnt_android.view.ui

import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.mnt_android.base.BaseActivity
import com.example.mnt_android.base.BaseViewModel
import com.example.mnt_android.databinding.ActivityGameBinding
import com.example.mnt_android.service.model.DoMission
import kotlinx.android.synthetic.main.activity_game.*
import com.kakao.kakaolink.v2.KakaoLinkResponse
import com.kakao.network.ErrorResult
import com.kakao.network.callback.ResponseCallback
import com.kakao.kakaolink.v2.KakaoLinkService
import java.util.HashMap
import com.kakao.message.template.*

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
        get() = com.example.mnt_android.R.layout.activity_game

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
        fragmentTransaction.replace(com.example.mnt_android.R.id.frag_game, fragment)
        fragmentTransaction.commit()
    }

    fun startMission() {

       var params = FeedTemplate
            .newBuilder(ContentObject.newBuilder("타이틀","https://lh3.googleusercontent.com/proxy/nzDAOkpvo9qjn2ZniqWhUrK7cv1g_Y5sZgCUt4hOZelp4jhMn3S0VMAxI9h2WQ9zavJYMWrISfKxAWPNBPa-HKPo6k8yVvZkG7n3cjfsUeG99EDsQ3XDRwuMK8e7znE",
                LinkObject.newBuilder().setAndroidExecutionParams("https://www.naver.com").build()).build())
            .addButton(ButtonObject("앱에서 보기",LinkObject.newBuilder().setWebUrl("'https://www.naver.com'").setMobileWebUrl("'https://www.naver.com'")
                .setAndroidExecutionParams("234").build())).build()

        var serverCallbackArgs  = HashMap<String, String>();
        var aa : Map<Any,Any> = HashMap<Any,Any>()


        var aaa  = object : ResponseCallback<KakaoLinkResponse>(){
            override fun onSuccess(result: KakaoLinkResponse?) {


            }

            override fun onFailure(errorResult: ErrorResult?) {

            }

        }

        KakaoLinkService.getInstance().sendDefault( this, params, serverCallbackArgs,aaa)




        val mission = DoMission("미션이름", "미션설명", "")
        Toast.makeText(this, "미션수행", Toast.LENGTH_SHORT).show()
        val intent = Intent(this@GameActivity, DoMissionActivity::class.java)
       // intent.putExtra("mission", mission)
      //  startActivity(intent)
    }
    fun createMission()
    {

        val intent = Intent(this@GameActivity,CreateMissionActivity::class.java)
        startActivity(intent)
    }

}
