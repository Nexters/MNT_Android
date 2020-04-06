package com.example.mnt_android.view.ui

import android.app.DatePickerDialog
import android.content.Intent
import android.content.SharedPreferences
import android.icu.text.SimpleDateFormat
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProviders
import com.example.mnt_android.R
import com.example.mnt_android.databinding.ActivityCreateroomBinding
import com.example.mnt_android.service.model.CheckRoom
import com.example.mnt_android.util.TAG_IS_MANAGER
import com.example.mnt_android.util.TAG_ROOM_ID
import com.example.mnt_android.view.ui.LoginActivity.Companion.editor
import com.example.mnt_android.viewmodel.BackPressViewModel
import com.example.mnt_android.viewmodel.CreateRoomViewModel
import com.google.firebase.iid.FirebaseInstanceId
import com.kakao.kakaolink.v2.KakaoLinkResponse
import com.kakao.kakaolink.v2.KakaoLinkService
import com.kakao.message.template.*
import com.kakao.network.ErrorResult
import com.kakao.network.callback.ResponseCallback
import kotlinx.android.synthetic.main.fragment_createroom3.*
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
    lateinit var sf : SharedPreferences
    lateinit var editor : SharedPreferences.Editor
    companion object {
        private const val IS_MANAGER = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil
            .setContentView<ActivityCreateroomBinding>(this, R.layout.activity_createroom)
        binding.lifecycleOwner = this // LiveData를 사용하기 위해서 없으면 Observe할때마다 refresh안딤

        sf = getSharedPreferences("login",0)
        editor = sf.edit()

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

        //내가 참여하고 있는 Room정보
        val checkRoom: CheckRoom? = intent.getParcelableExtra("checkRoom")
        val fragNum = intent.getIntExtra("fragNum",0)
        if(checkRoom!=null)
        {
            //MainActivity에서 방이 존재한다고 판단하여 방정보를 넘김
            createRoomViewModel.fragmentNum=fragNum
            createRoomViewModel.room.value=checkRoom.room
            createRoomViewModel.id=checkRoom.room.id
            setFrag(fragNum)

        }

        createRoomViewModel.isCreated.observe(this,androidx.lifecycle.Observer {
            if(it==true)
                setFrag(2)
        })

        createRoomViewModel.isStarted.observe(this,androidx.lifecycle.Observer {
            if(it==1)
            {
                editor.putLong("roomId", createRoomViewModel.roomId.roomId)
                editor!!.putBoolean("isManager", true)
                editor!!.commit()
                val intent  =Intent(this,GameActivity::class.java)
                intent.putExtra(TAG_IS_MANAGER, IS_MANAGER)
                intent.putExtra(TAG_ROOM_ID, createRoomViewModel.id)
                intent.putExtra("roomName",createRoomViewModel.name)
                startActivity(intent)
            }
            else if(it==2)
            {
                Toast.makeText(this,"방 인원이 충분하지 않습니다.",Toast.LENGTH_LONG).show()
            }
        })

    }

    fun setDate(index : Int)
    {
        val today = Date()
        var strdate: String? = null

        var format1 = SimpleDateFormat()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            format1 = SimpleDateFormat("yyyy-MM-dd")
            strdate = format1.format(today)
        }

        val dialog = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->

            if(index==1)
            {
                createRoomViewModel.startDay.value="$year-${month+1}-$dayOfMonth"
            }
            else
            {
                createRoomViewModel.endDay.value= "$year-${month+1}-$dayOfMonth"
            }


        },  strdate!!.split("-")[0].toInt(),strdate!!.split("-")[1].toInt()-1,strdate!!.split("-")[2].toInt())


        dialog.show()




    }

    fun sendKakaoLink(roomnum : Long)
    {
    var params = TextTemplate
        .newBuilder("마니또를 생성하였습니다", LinkObject.newBuilder().setAndroidExecutionParams("https://www.naver.com").build())
        .addButton(ButtonObject("앱에서 보기",LinkObject.newBuilder().setWebUrl("'https://www.naver.com'").setMobileWebUrl("'https://www.naver.com'")
            .setAndroidExecutionParams("roomnum=$roomnum").setIosExecutionParams("roomnum=$roomnum").build())).build()

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
                //backPressViewModel.onBackPressed(this)
                finish()
            }
            1->
            {
                setFrag(0)
            }
            2->
            {
                backPressViewModel.onBackPressed(this)
            }
        }

    }


    fun onClick(v: View) {
        when(v) {
            bu_lookparticipant_createroom3 -> {
                changeActivity(ApplicantListActivity::class.java)
            }
        }
    }

    private fun <T> changeActivity(cls : Class<T>){
        val intent = Intent(this, cls)
        intent.putExtra(TAG_IS_MANAGER, IS_MANAGER)
        intent.putExtra(TAG_ROOM_ID, createRoomViewModel.id)
        startActivity(intent)
    }

}