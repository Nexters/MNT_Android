package com.example.mnt_android.View.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProviders
import com.example.mnt_android.R
import com.example.mnt_android.viewmodel.BackPressViewModel
import com.example.mnt_android.viewmodel.CreateRoomViewModel
import com.kakao.kakaolink.v2.KakaoLinkResponse
import com.kakao.kakaolink.v2.KakaoLinkService
import com.kakao.message.template.*
import com.kakao.network.ErrorResult
import com.kakao.network.callback.ResponseCallback
import java.util.*

class CreateRoomActivity :FragmentActivity()
{
    lateinit var createRoomViewModel: CreateRoomViewModel
    lateinit var createRoomFragment : CreateRoomFragment
    lateinit var createRoomFragment2 : CreateRoomFragment2
    lateinit var createRoomFragment3 : CreateRoomFragment3
    lateinit var fragmentTransaction: FragmentTransaction
    lateinit var fragmentManager: FragmentManager
    lateinit var backPressViewModel : BackPressViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_createroom)



        createRoomViewModel = ViewModelProviders.of(this)[CreateRoomViewModel::class.java]
        backPressViewModel=  ViewModelProviders.of(this)[BackPressViewModel::class.java]

        fragmentManager =supportFragmentManager
        fragmentTransaction = fragmentManager.beginTransaction()
        createRoomFragment= CreateRoomFragment()
        createRoomFragment2= CreateRoomFragment2()
        createRoomFragment3= CreateRoomFragment3()

        setFrag(0)

        val intent = intent
        val str = intent.data
        if(str!=null)
            Toast.makeText(this@CreateRoomActivity,intent.data.getQueryParameter("roomnum").toString(),Toast.LENGTH_LONG).show()


    }

    fun setRoomInfo()
    {
        createRoomViewModel.setRoomInfo()
        Log.d("wlgusdnzzz",createRoomViewModel.roomInfo.num.value.toString()+createRoomViewModel.roomInfo.name.value.toString())
        setFrag(1)
    }

    fun sendKakaoLink(roomnum : String)
    {
        var params = TextTemplate
            .newBuilder("마니또를 생성하였습니다", LinkObject.newBuilder().setAndroidExecutionParams("https://www.naver.com").build())
            .addButton(ButtonObject("앱에서 보기",LinkObject.newBuilder().setWebUrl("'https://www.naver.com'").setMobileWebUrl("'https://www.naver.com'")
                .setAndroidExecutionParams("roomnum=$roomnum").build())).build()

        var serverCallbackArgs  = HashMap<String, String>();
        var aa : Map<Any,Any> = HashMap<Any,Any>()


        var aaa  = object : ResponseCallback<KakaoLinkResponse>(){
            override fun onSuccess(result: KakaoLinkResponse?) {


            }

            override fun onFailure(errorResult: ErrorResult?) {

            }

        }

        KakaoLinkService.getInstance().sendDefault( this, params, serverCallbackArgs,aaa)

    }

    fun setFrag(n : Int)
    {
        fragmentTransaction = fragmentManager.beginTransaction()

        when(n)
        {
            0 ->
            {
                fragmentTransaction.replace(R.id.frag_createroom,createRoomFragment)
                createRoomViewModel.fragmentNum=0
                fragmentTransaction.commit()

            }
            1->
            {
                fragmentTransaction.replace(R.id.frag_createroom,createRoomFragment2)
                createRoomViewModel.fragmentNum=1
                fragmentTransaction.commit()
            }
            2->
            {
                fragmentTransaction.replace(R.id.frag_createroom,createRoomFragment3)
                createRoomViewModel.fragmentNum=2
                fragmentTransaction.commit()
            }
        }
    }

    override fun onBackPressed() {

        when(createRoomViewModel.fragmentNum)
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