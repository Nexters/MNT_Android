package com.nexters.frutto.view.ui

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProviders
import com.nexters.frutto.R
import com.nexters.frutto.databinding.ActivityCreateMissionBinding
import com.nexters.frutto.databinding.ActivityLoginBinding
import com.nexters.frutto.viewmodel.BackPressViewModel
import com.nexters.frutto.viewmodel.CreateMissionViewModel

class CreateMissionActivity : AppCompatActivity()
{
    lateinit var createMissionViewModel: CreateMissionViewModel
    lateinit var createMissionFragment : CreateMissionFragment
    lateinit var fragmentTransaction: FragmentTransaction
    lateinit var fragmentManager: FragmentManager
    lateinit var backPressViewModel : BackPressViewModel
    lateinit var sf : SharedPreferences
    var roomId : Long = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil
            .setContentView<ActivityCreateMissionBinding>(this, R.layout.activity_create_mission)
        binding.lifecycleOwner = this // LiveData를 사용하기 위해서 없으면 Observe할때마다 refresh안딤
        backPressViewModel = ViewModelProviders.of(this)[BackPressViewModel::class.java]
        createMissionViewModel=ViewModelProviders.of(this)[CreateMissionViewModel::class.java]
        createMissionFragment= CreateMissionFragment()
        fragmentManager =supportFragmentManager
        fragmentTransaction = fragmentManager.beginTransaction()

        sf = getSharedPreferences("login", 0)

        roomId = sf.getLong("roomId",0)

        setFrag(0)



    }

    fun setFrag(n : Int)
    {
        fragmentTransaction = fragmentManager.beginTransaction()

        when(n)
        {
            0 ->
            {
                fragmentTransaction.replace(R.id.frag_createmission,createMissionFragment)
                createMissionViewModel.fragmentNum=0
                fragmentTransaction.commit()

            }


        }
    }

    override fun onBackPressed() {

        when(createMissionViewModel.fragmentNum)
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

        }

    }

}