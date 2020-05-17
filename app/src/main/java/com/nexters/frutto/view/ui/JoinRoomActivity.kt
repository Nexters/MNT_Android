package com.nexters.frutto.view.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.nexters.frutto.R
import com.nexters.frutto.databinding.ActivityJoinroomBinding
import com.nexters.frutto.extension.noticeDate
import com.nexters.frutto.service.model.CheckRoom
import com.nexters.frutto.service.repository.PreferencesRepository
import com.nexters.frutto.viewmodel.JoinRoomViewModel
import com.nexters.frutto.viewmodel.BackPressViewModel
import com.nexters.frutto.service.KakaoMessageService

class JoinRoomActivity : AppCompatActivity()
{
    lateinit var joinRoomViewModel: JoinRoomViewModel
    lateinit var joinRoomFragment : JoinRoomFragment
    lateinit var joinRoomFragment2 : JoinRoomFragment2
    lateinit var joinRoomFragment3 : JoinRoomFragment3
    lateinit var fragmentTransaction: FragmentTransaction
    lateinit var fragmentManager: FragmentManager
    lateinit var backPressViewModel : BackPressViewModel
    var checkRoom : CheckRoom?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil
            .setContentView<ActivityJoinroomBinding>(this, R.layout.activity_joinroom)
        binding.lifecycleOwner = this
        joinRoomViewModel = ViewModelProviders.of(this)[JoinRoomViewModel::class.java]
        backPressViewModel=  ViewModelProviders.of(this)[BackPressViewModel::class.java]
       fragmentManager=supportFragmentManager
        fragmentTransaction=fragmentManager.beginTransaction()
        joinRoomFragment= JoinRoomFragment()
        joinRoomFragment2= JoinRoomFragment2()
        joinRoomFragment3 = JoinRoomFragment3()

        setFrag(0)

        joinRoomViewModel.checkNitto.observe(this, Observer {
            if(it==true)
            {
                val sf = getSharedPreferences("login", 0)
                val editor = sf.edit()
                editor.putBoolean("check",true)
               editor.commit()

                checkNitto()
            }
        })

        joinRoomViewModel.isSearched.observe(this, Observer {
            if(it==true)
                setFrag(1)
            else
            {
                Toast.makeText(this@JoinRoomActivity,"참여코드의 방이 존재하지 않습니다.",Toast.LENGTH_SHORT).show()
            }
        })

        joinRoomViewModel.isLogined.observe(this, Observer {
            if(it==false)
            {
                val intent = Intent(this,LoginActivity::class.java)
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
                startActivity(intent)
            }
        })

        val intent = intent
        checkRoom = intent.getParcelableExtra("checkRoom")
        val roomId : Int?=intent.getIntExtra("roomNum",0)
        val fragNum = intent.getIntExtra("fragNum",0)
        Log.d("wlgusdnzzz",roomId.toString())
        if(checkRoom!=null)
        {
            //MainActivity에서 방이 존재한다고 판단하여 방정보를 넘김
            joinRoomViewModel.fragmentNum=fragNum
            joinRoomViewModel.checkRoom.value=checkRoom
            joinRoomViewModel.startDayText_joinroom2="${joinRoomViewModel.checkRoom.value!!.room.startDay.noticeDate} 에 시작합니다.\n친구들이 모일때까지 잠시 기다려주세요\uD83D\uDC4F"
            joinRoomViewModel.startDayText_joinroom3="${joinRoomViewModel.checkRoom.value!!.room.startDay} 까지 프루또 친구를 많이 많이 챙겨주세요!\uD83D\uDC8C"

            setFrag(fragNum)

        }
        if(roomId!=0)
        {
            joinRoomViewModel.fragmentNum=fragNum
            joinRoomViewModel.roomInfo.num.value=roomId.toString()
        }


    }

    fun checkNitto()
    {

        //여기서 니또 확인 sf 저장

        val intent = Intent(this@JoinRoomActivity,GameActivity::class.java)
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
        intent.putExtra("roomName",checkRoom?.room?.name)
        startActivity(intent)
        finish()
    }

    fun lookParticipants()
    {
        val intent = Intent(this,ApplicantListActivity::class.java)
        intent.putExtra("isManager",0)
        intent.putExtra("roomId",PreferencesRepository(this).getRoomId())
        startActivity(intent)
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
                fragmentTransaction.replace(R.id.frag_joinroom,joinRoomFragment3)
                joinRoomViewModel.fragmentNum=2
                fragmentTransaction.commit()
            }

        }
    }

    fun sendKakaoLink(roomNm: String, roomNum : Long) = KakaoMessageService.sendRoomNum(baseContext, roomNm, roomNum)

    override fun onBackPressed() {

        when(joinRoomViewModel.fragmentNum)
        {
            0->
            {
                finish()
            }
            1->
            {
                backPressViewModel.onBackPressed(this)
            }
            2->
            {
                backPressViewModel.onBackPressed(this)
            }
        }

    }



}