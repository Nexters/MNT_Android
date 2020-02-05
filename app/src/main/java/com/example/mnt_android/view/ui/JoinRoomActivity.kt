package com.example.mnt_android.view.ui

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.mnt_android.R
import com.example.mnt_android.viewmodel.JoinRoomViewModel
import com.example.mnt_android.viewmodel.BackPressViewModel
import com.kakao.kakaolink.v2.KakaoLinkResponse
import com.kakao.kakaolink.v2.KakaoLinkService
import com.kakao.message.template.ButtonObject
import com.kakao.message.template.LinkObject
import com.kakao.message.template.TextTemplate
import com.kakao.network.ErrorResult
import com.kakao.network.callback.ResponseCallback
import kotlinx.android.synthetic.main.fragment_joinroom3.*
import java.util.HashMap

class JoinRoomActivity : AppCompatActivity()
{
    lateinit var joinRoomViewModel: JoinRoomViewModel
    lateinit var joinRoomFragment : JoinRoomFragment
    lateinit var joinRoomFragment2 : JoinRoomFragment2
    lateinit var joinRoomFragment3 : JoinRoomFragment3
    lateinit var fragmentTransaction: FragmentTransaction
    lateinit var fragmentManager: FragmentManager
    lateinit var backPressViewModel : BackPressViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_joinroom)

        joinRoomViewModel = ViewModelProviders.of(this)[JoinRoomViewModel::class.java]
        backPressViewModel=  ViewModelProviders.of(this)[BackPressViewModel::class.java]

        fragmentManager=supportFragmentManager
        fragmentTransaction=fragmentManager.beginTransaction()
        joinRoomFragment= JoinRoomFragment()
        joinRoomFragment2= JoinRoomFragment2()
        joinRoomFragment3 = JoinRoomFragment3()
        val sf : SharedPreferences = getSharedPreferences("login",0)


        if(sf.getBoolean("checkNitto",false))
        {
            //입장한 방이 있고 내 니또를 확인했다.
            val intent = Intent(this@JoinRoomActivity,ManittoActivity::class.java)
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
            startActivity(intent)
            finish()
        }
        else
        {
            //입장한 방이 있지만 내 니또를 확인하지 못했다.
            setFrag(2)
        }

        joinRoomViewModel.checkNitto.observe(this, Observer {
            if(it)
                checkNitto()
        })


        val intent = intent
        val str = intent.data
        if(str!=null)
        {
            //카카오 링크를 통해 들어옴
            joinRoomViewModel.findRoom(intent.data.getQueryParameter("roomnum").toString())
            //만약 내가 내 마니또 보는 화면을 봤으면 바로 타임라인 Actviity로 이동
        }

    }

    fun checkNitto()
    {
        val intent = Intent(this@JoinRoomActivity,GameActivity::class.java)
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
        intent.putExtra(GameActivity.TAG_IS_MANAGER, isManager_cb.isChecked)
        startActivity(intent)
        finish()
    }

    fun setFrag(n : Int)
    {
        fragmentTransaction = fragmentManager.beginTransaction()

        when(n)
        {
            0 ->
            {
                fragmentTransaction.replace(R.id.frag_joinroom,joinRoomFragment)
                joinRoomViewModel.fragmentNum=0
                fragmentTransaction.commit()

            }
            1->
            {
                fragmentTransaction.replace(R.id.frag_joinroom,joinRoomFragment2)
                joinRoomViewModel.fragmentNum=1
                fragmentTransaction.commit()
            }
            2 ->
            {
                if(true)
                {
                    //방이 시작되지 않았으면 Fragment3으로 넘어가지 않는다
                }
                else
                {
                    //방이 시작되었으므로 Fragment3으로 넘어간다.
                }
                fragmentTransaction.replace(R.id.frag_joinroom,joinRoomFragment3)
                joinRoomViewModel.fragmentNum=2
                fragmentTransaction.commit()
            }

        }
    }

    fun sendKakaoLink(roomnum : String)
    {
        var params = TextTemplate
            .newBuilder("마니또를 생성하였습니다", LinkObject.newBuilder().setAndroidExecutionParams("https://www.naver.com").build())
            .addButton(
                ButtonObject("앱에서 보기", LinkObject.newBuilder().setWebUrl("'https://www.naver.com'").setMobileWebUrl("'https://www.naver.com'")
                    .setAndroidExecutionParams("roomnum=$roomnum").build())
            ).build()

        var serverCallbackArgs  = HashMap<String, String>();


        var aaa  = object : ResponseCallback<KakaoLinkResponse>(){
            override fun onSuccess(result: KakaoLinkResponse?) {


            }

            override fun onFailure(errorResult: ErrorResult?) {

            }

        }

        KakaoLinkService.getInstance().sendDefault( this, params, serverCallbackArgs,aaa)

    }

    fun joinRoom(roomnum : String)
    {
       joinRoomViewModel.findRoom(roomnum)

        setFrag(1)
    }

    override fun onBackPressed() {

        when(joinRoomViewModel.fragmentNum)
        {
            0->
            {
                backPressViewModel.onBackPressed(this)
            }
            1->
            {
                setFrag(0)
            }
            2->
            {
                setFrag(1)
            }
        }

    }



}