package com.example.mnt_android.view.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProviders
import com.example.mnt_android.R
import com.example.mnt_android.viewmodel.JoinRoomViewModel
import com.example.mnt_android.view.ui.CreateRoomFragment
import com.example.mnt_android.view.ui.CreateRoomFragment2
import com.example.mnt_android.viewmodel.BackPressViewModel
import com.example.mnt_android.viewmodel.CreateRoomViewModel

class JoinRoomActivity : AppCompatActivity()
{
    lateinit var joinRoomViewModel: JoinRoomViewModel
    lateinit var joinRoomFragment : JoinRoomFragment
    lateinit var joinRoomFragment2 : JoinRoomFragment2
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

        setFrag(0)

        val intent = intent
        val str = intent.data
        if(str!=null)
        {
            joinRoomViewModel.findRoom(intent.data.getQueryParameter("roomnum").toString())
        }



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

        }
    }

    fun joinRoom(roomnum : String)
    {
        Toast.makeText(this,joinRoomViewModel.roomInfo.num.value.toString(),Toast.LENGTH_SHORT).show()
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