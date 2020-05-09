package com.nexters.frutto.view.ui

import android.animation.ObjectAnimator
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Point
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import com.nexters.frutto.R
import com.nexters.frutto.base.BaseActivity
import com.nexters.frutto.base.BaseViewModel
import com.nexters.frutto.bus.SWIPE_DOWN
import com.nexters.frutto.bus.SWIPE_UP
import com.nexters.frutto.bus.swipeEventBus
import com.nexters.frutto.databinding.ActivityGameBinding
import com.nexters.frutto.view.listener.SwipeDetecter
import com.nexters.frutto.viewmodel.BackPressViewModel
import com.nexters.frutto.viewmodel.GameViewModel
import kotlinx.android.synthetic.main.activity_game.*
import com.kakao.kakaolink.v2.KakaoLinkResponse
import com.kakao.network.ErrorResult
import com.kakao.network.callback.ResponseCallback
import com.kakao.kakaolink.v2.KakaoLinkService
import java.util.HashMap
import com.kakao.message.template.*
import io.reactivex.disposables.CompositeDisposable
import org.jetbrains.anko.textColor
import org.koin.android.viewmodel.ext.android.viewModel

class GameActivity : BaseActivity<ActivityGameBinding, BaseViewModel>(), View.OnClickListener {
    override var viewModel = BaseViewModel()
    override val layoutId: Int
        get() = R.layout.activity_game

    private val swipeDetecter = SwipeDetecter()

    private lateinit var fragmentManager: FragmentManager
    private lateinit var fragmentTransaction: FragmentTransaction

    private lateinit var backPressViewModel: BackPressViewModel
    val gameViewModel by viewModel<GameViewModel>()
    private lateinit var timeLineFragment: TimeLineFragment
    private lateinit var missionApplicantFragment: MissionApplicantFragment
    private lateinit var missionManagerFragment: MissionManagerFragment
    private lateinit var dashBoardApplicantFragment: DashBoardApplicantFragment
    private lateinit var dashBoardManagerFragment: DashBoardManagerFragment

    private val disposable = CompositeDisposable()
    private var screenMaxHeight: Float = 0f
    private var screenMinHeight: Float = 0f

    private var selectedBtn : ImageView? = null
    private var selectedTv : TextView? = null
     lateinit var sharedPreferences  : SharedPreferences

    override fun initSetting() {
        sharedPreferences = getSharedPreferences("login",0)
        val binding = DataBindingUtil
            .setContentView<ActivityGameBinding>(this, R.layout.activity_game)

        binding.lifecycleOwner = this
        timeLineFragment = TimeLineFragment()

        Log.d("wlgusdnzzz",sharedPreferences.getLong("roomId",0).toString())

       if(sharedPreferences.getBoolean("isManager",false))
        {
            missionManagerFragment = MissionManagerFragment()
        }
        else
        {
            missionApplicantFragment = MissionApplicantFragment()
        }
        dashBoardApplicantFragment = DashBoardApplicantFragment()
        dashBoardManagerFragment = DashBoardManagerFragment()
        backPressViewModel = ViewModelProvider(this).get(BackPressViewModel::class.java)
        gameViewModel.title = intent.getStringExtra("roomName") ?: ""
        fragmentManager = supportFragmentManager


        changeFragment(timeLineFragment)
        selectTimeLineBtn()



    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        swipeDetecter.onTouch(game_layout, ev)
        return super.dispatchTouchEvent(ev)
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            eventBusSetting()
        } else {
            clearEventBus()
        }
    }

    private fun getScreenHeight() : Float {
        val display = windowManager.defaultDisplay
        val point = Point()
        display.getSize(point)
        return point.y.toFloat()
    }


    // TODO : 메뉴바 코드 나중에 정리 필요
    private fun eventBusSetting() {
        if (screenMinHeight == 0f) {
            screenMinHeight = floating_menu_layout.y
            screenMaxHeight = getScreenHeight()
        }

        disposable.add(swipeEventBus.subscribe {
            when(it) {
                SWIPE_UP -> hideMenuBar(floating_menu_layout)
                SWIPE_DOWN -> showMenuBar(floating_menu_layout)
                else -> showMenuBar(floating_menu_layout)
            }
        })
    }

    private fun clearEventBus() = disposable.clear()

    private fun hideMenuBar(v: View) = moveMenuBar(v, screenMaxHeight - screenMinHeight).start()

    private fun showMenuBar(v: View) = moveMenuBar(v, 0f).start()

    private fun moveMenuBar(view: View, translationY: Float) =
        ObjectAnimator.ofFloat(view, "translationY", translationY).apply {
            duration = 300
        }

    override fun onBackPressed() {

        backPressViewModel.onBackPressed(this)

    }

    override fun onClick(v: View?) {
        when (v) {
            dashboard_layout -> {
                if(sharedPreferences.getBoolean("isManager",false))
                {
                    changeFragment(dashBoardManagerFragment)
                }
                else
                {
                    changeFragment(dashBoardApplicantFragment)
                }
                selectDashBoardBtn()
            }
            feed_layout -> {
                changeFragment(timeLineFragment)
                selectTimeLineBtn()
            }
            mission_layout -> {
                if(sharedPreferences.getBoolean("isManager",false))
                {
                    changeFragment(missionManagerFragment)
                }
                else
                {
                    changeFragment(missionApplicantFragment)
                }
                selectMissionBtn()
            }
        }
    }

    private fun changeFragment(fragment: Fragment) {
        fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.game_layout, fragment)
        fragmentTransaction.commit()
    }


    fun selectBtn(btn: ImageView, tv: TextView) {
        selectedBtn?.isSelected = false
        selectedTv?.textColor = getResColor(R.color.colorLightGray2)
        btn.isSelected = true
        tv.textColor = getResColor(R.color.colorSelected)
        selectedBtn = btn
        selectedTv = tv
    }

    fun selectDashBoardBtn() = selectBtn(dashboard_iv, dashboard_tv)
    fun selectTimeLineBtn() = selectBtn(feed_iv, feed_tv)
    fun selectMissionBtn() = selectBtn(mission_iv, mission_tv)

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




       // intent.putExtra("mission", mission)
      //  startActivity(intent)
    }
    fun createMission()
    {

        val intent = Intent(this@GameActivity,CreateMissionActivity::class.java)

        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()

       if(sharedPreferences.getBoolean("isManager",false)) {

       }
        else {

           Log.d("wlgusdnzzz","참가자")
       }
    }

}
