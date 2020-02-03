package com.example.mnt_android.view.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProviders
import com.example.mnt_android.R
import com.example.mnt_android.viewmodel.BackPressViewModel
import com.example.mnt_android.viewmodel.DoMissionViewModel

class DoMissionActivity : AppCompatActivity()
{

    lateinit var doMissionViewModel: DoMissionViewModel
    lateinit var doMissionFragment : DoMissionFragment
    lateinit var doMissionFragment2 : DoMissionFragment2
    lateinit var fragmentTransaction: FragmentTransaction
    lateinit var fragmentManager: FragmentManager
    lateinit var backPressViewModel : BackPressViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_do_mission)

        doMissionViewModel = ViewModelProviders.of(this)[DoMissionViewModel::class.java]
        backPressViewModel=  ViewModelProviders.of(this)[BackPressViewModel::class.java]

        fragmentManager=supportFragmentManager
        fragmentTransaction=fragmentManager.beginTransaction()
        doMissionFragment= DoMissionFragment()
        doMissionFragment2= DoMissionFragment2()

        doMissionViewModel.doMission.value = intent.getParcelableExtra("mission")


            setFrag(0)




    }



    fun setFrag(n : Int)
    {
        fragmentTransaction = fragmentManager.beginTransaction()

        when(n)
        {
            0 ->
            {
                fragmentTransaction.replace(R.id.frag_do_mission,doMissionFragment)
                doMissionViewModel.fragmentNum=0
                fragmentTransaction.commit()

            }
            1->
            {
               doMissionViewModel.setMission()
                fragmentTransaction.replace(R.id.frag_do_mission,doMissionFragment2)
                doMissionViewModel.fragmentNum=1
                fragmentTransaction.commit()
            }
            2->
            {
                Toast.makeText(this,"알림팝업 띄우기",Toast.LENGTH_SHORT).show()
                finish()
            }


        }
    }


    fun setImage()
    {
        Toast.makeText(this,"Select Image",Toast.LENGTH_SHORT).show()
    }


    override fun onBackPressed() {

        when(doMissionViewModel.fragmentNum)
        {
            0->
            {
                finish()
            }
            1->
            {
                setFrag(0)
            }

        }

    }


}