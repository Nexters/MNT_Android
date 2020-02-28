package com.example.mnt_android.view.ui

import android.animation.ObjectAnimator
import android.content.Context
import android.content.Intent
import android.graphics.Point
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProviders
import com.example.mnt_android.R
import com.example.mnt_android.base.BaseActivity
import com.example.mnt_android.base.BaseViewModel
import com.example.mnt_android.bus.SWIPE_DOWN
import com.example.mnt_android.bus.SWIPE_UP
import com.example.mnt_android.bus.swipeEventBus
import com.example.mnt_android.databinding.ActivityGameBinding
import com.example.mnt_android.service.model.CheckRoom
import com.example.mnt_android.util.TAG_IS_MANAGER
import com.example.mnt_android.view.listener.SwipeDetecter
import com.example.mnt_android.viewmodel.BackPressViewModel
import kotlinx.android.synthetic.main.activity_game.*
import com.kakao.kakaolink.v2.KakaoLinkResponse
import com.kakao.network.ErrorResult
import com.kakao.network.callback.ResponseCallback
import com.kakao.kakaolink.v2.KakaoLinkService
import java.util.HashMap
import com.kakao.message.template.*
import io.reactivex.disposables.CompositeDisposable
import org.jetbrains.anko.textColor

class GameActivity : BaseActivity<ActivityGameBinding, BaseViewModel>(), View.OnClickListener {
    override var viewModel = BaseViewModel()
    override val layoutId: Int
        get() = R.layout.activity_game

    private lateinit var fragmentManager: FragmentManager
    private lateinit var fragmentTransaction: FragmentTransaction

    private lateinit var backPressViewModel: BackPressViewModel
    private lateinit var timeLineFragment: TimeLineFragment
    private lateinit var missionFragment: GameMissionFragment
    private lateinit var dashBoardApplicantFragment: DashBoardApplicantFragment
    private lateinit var dashBoardManagerFragment: DashBoardManagerFragment

    private lateinit var checkRoom : CheckRoom

    private val disposable = CompositeDisposable()
    private var screenMaxHeight: Float = 0f
    private var screenMinHeight: Float = 0f

    private var selectedBtn : ImageView? = null
    private var selectedTv : TextView? = null


    override fun initSetting() {
        val isManager = intent.getBooleanExtra(TAG_IS_MANAGER, false)
        timeLineFragment = TimeLineFragment(isManager)
        missionFragment = GameMissionFragment()
        dashBoardApplicantFragment = DashBoardApplicantFragment()
        dashBoardManagerFragment = DashBoardManagerFragment()
        backPressViewModel = ViewModelProviders.of(this)[BackPressViewModel::class.java]

        fragmentManager = supportFragmentManager

        changeFragment(timeLineFragment)
        selectBtn(feed_iv, feed_tv)
    }

    override fun onCreateView(
        parent: View?,
        name: String,
        context: Context,
        attrs: AttributeSet
    ): View? {
        parent?.setOnTouchListener(SwipeDetecter())
        return super.onCreateView(parent, name, context, attrs)
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
                changeFragment(dashBoardApplicantFragment)
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




       // intent.putExtra("mission", mission)
      //  startActivity(intent)
    }
    fun doMission()
    {

        val intent = Intent(this@GameActivity,DoMissionActivity::class.java)

        startActivity(intent)
    }
    fun createMission()
    {

        val intent = Intent(this@GameActivity,CreateMissionActivity::class.java)

        startActivity(intent)
    }
}
