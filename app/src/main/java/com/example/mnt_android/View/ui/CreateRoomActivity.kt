package com.example.mnt_android.view.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.mnt_android.R
import com.example.mnt_android.databinding.ActivityCreateroomBinding
import com.example.mnt_android.databinding.ActivityLoginBinding
import com.example.mnt_android.databinding.ActivityMainBinding
import com.example.mnt_android.viewmodel.CreateRoomViewModel
import com.example.mnt_android.viewmodel.LoginViewModel
import java.util.*

class CreateRoomActivity :FragmentActivity()
{
    lateinit var createRoomViewModel: CreateRoomViewModel
    lateinit var createRoomFragment : CreateRoomFragment
    lateinit var createRoomFragment2 : CreateRoomFragment2
    lateinit var createRoomFragment3 : CreateRoomFragment3
    lateinit var fragmentTransaction: FragmentTransaction
    lateinit var fragmentManager: FragmentManager
    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_createroom)



        createRoomViewModel = ViewModelProviders.of(this)[CreateRoomViewModel::class.java]

        fragmentManager =supportFragmentManager
        fragmentTransaction = fragmentManager.beginTransaction()
        createRoomFragment= CreateRoomFragment()
        createRoomFragment2= CreateRoomFragment2()
        createRoomFragment3= CreateRoomFragment3()

        setFrag(0)


    }

    fun setRoomInfo()
    {
        createRoomViewModel.roomInfo.num.value= Random(10000).nextInt()
        Log.d("wlgusdnzzz",createRoomViewModel.roomInfo.num.value.toString()+createRoomViewModel.roomInfo.name.value.toString())
        setFrag(1)
    }

    fun setFrag(n : Int)
    {
        fragmentTransaction = fragmentManager.beginTransaction()

        when(n)
        {
            0 ->
            {
                fragmentTransaction.replace(R.id.frag1,createRoomFragment)
                createRoomViewModel.fragmentNum=0
                fragmentTransaction.commit()

            }
            1->
            {
                fragmentTransaction.replace(R.id.frag1,createRoomFragment2)
                createRoomViewModel.fragmentNum=1
                fragmentTransaction.commit()
            }
            2->
            {
                fragmentTransaction.replace(R.id.frag1,createRoomFragment3)
                createRoomViewModel.fragmentNum=2
                fragmentTransaction.commit()
            }
        }
    }

    override fun onBackPressed() {

        when(createRoomViewModel.fragmentNum)
        {
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