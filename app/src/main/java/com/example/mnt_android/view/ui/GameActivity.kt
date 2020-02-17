package com.example.mnt_android.view.ui

import android.content.Intent
import android.graphics.Point
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.mnt_android.R
import com.example.mnt_android.base.BaseActivity
import com.example.mnt_android.base.BaseViewModel
import com.example.mnt_android.bus.scrollEventBus
import com.example.mnt_android.databinding.ActivityGameBinding
import com.example.mnt_android.service.model.DoMission
import com.example.mnt_android.util.TAG_IS_MANAGER
import kotlinx.android.synthetic.main.activity_game.*
import com.kakao.kakaolink.v2.KakaoLinkResponse
import com.kakao.network.ErrorResult
import com.kakao.network.callback.ResponseCallback
import com.kakao.kakaolink.v2.KakaoLinkService
import java.util.HashMap
import com.kakao.message.template.*
import io.reactivex.disposables.CompositeDisposable
import org.jetbrains.anko.textColor
import kotlin.math.absoluteValue

class GameActivity : BaseActivity<ActivityGameBinding, BaseViewModel>(), View.OnClickListener {
    override var viewModel = BaseViewModel()
    override val layoutId: Int
        get() = R.layout.activity_game

    private lateinit var fragmentManager: FragmentManager
    private lateinit var fragmentTransaction: FragmentTransaction

    private lateinit var timeLineFragment: TimeLineFragment
    private lateinit var missionFragment: GameMissionFragment
    private lateinit var dashBoardApplicantFragment: DashBoardApplicantFragment
    private lateinit var dashBoardManagerFragment: DashBoardManagerFragment

    private val disposable = CompositeDisposable()
    private var screenMaxHeight: Int = 0
    private var screenMinHeight: Int = 0

    private var selectedBtn : ImageView? = null
    private var selectedTv : TextView? = null

    override fun initSetting() {
        val isManager = intent.getBooleanExtra(TAG_IS_MANAGER, false)

        timeLineFragment = TimeLineFragment(isManager)
        missionFragment = GameMissionFragment()
        dashBoardApplicantFragment = DashBoardApplicantFragment()
        dashBoardManagerFragment = DashBoardManagerFragment()

        fragmentManager = supportFragmentManager

        changeFragment(timeLineFragment)
        selectBtn(feed_iv, feed_tv)
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            eventBusSetting()
        } else {
            clearEventBus()
        }
    }

    private fun getScreenHeight() : Int {
        val display = windowManager.defaultDisplay
        val point = Point()
        display.getSize(point)
        return point.y
    }

    private fun eventBusSetting() {
        if (screenMinHeight == 0) {
            screenMinHeight = floating_menu_layout.y.toInt()
        }
        screenMaxHeight = getScreenHeight()

        disposable.add(scrollEventBus.subscribe {
            val y = floating_menu_layout.y
            val maxDiff = screenMaxHeight - screenMinHeight

            floating_menu_layout.y += if (it.absoluteValue > maxDiff) {
                if (it > 0) maxDiff else maxDiff * -1
            } else if (it > 0 && y < screenMaxHeight) {
                it
            } else if (it < 0 && y > screenMinHeight) {
                it
            } else {
                0
            }
        })
    }

    private fun clearEventBus() {
        disposable.clear()
    }

    override fun onClick(v: View?) {
        when (v) {
            dashboard_layout -> {
                changeFragment(dashBoardManagerFragment)
                selectBtn(dashboard_iv, dashboard_tv)
            }
            feed_layout -> {
                changeFragment(timeLineFragment)
                selectBtn(feed_iv, feed_tv)
            }
            mission_layout -> {
                changeFragment(missionFragment)
                selectBtn(mission_iv, mission_tv)
            }
        }
        floating_menu_layout.y = screenMinHeight.toFloat()
    }

    private fun changeFragment(fragment: Fragment) {
        fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(game_layout.id, fragment)
        fragmentTransaction.commit()
    }


    private fun selectBtn(btn: ImageView, tv: TextView) {
        selectedBtn?.isSelected = false
        selectedTv?.textColor = getResColor(R.color.colorLightGray2)
        btn.isSelected = true
        tv.textColor = getResColor(R.color.colorSelected)
        selectedBtn = btn
        selectedTv = tv
    }

    private fun getResColor(colorId: Int) = ResourcesCompat.getColor(resources, colorId, null)

    fun startMission() {

       var params = FeedTemplate
            .newBuilder(ContentObject.newBuilder("타이틀","https://images.mypetlife.co.kr/wp-content/uploads/2018/03/06153453/KakaoTalk_20180228_153728551.jpg",
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
