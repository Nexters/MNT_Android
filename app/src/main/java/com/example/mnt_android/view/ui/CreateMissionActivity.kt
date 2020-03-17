package com.example.mnt_android.view.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Resources
import android.os.Bundle
import android.transition.Visibility
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mnt_android.R
import com.example.mnt_android.base.BaseActivity
import com.example.mnt_android.base.BaseViewModel
import com.example.mnt_android.databinding.ActivityCreateMissionBinding
import com.example.mnt_android.databinding.ActivityLoginBinding
import com.example.mnt_android.generated.callback.OnClickListener
import com.example.mnt_android.service.model.CheckRoom
import com.example.mnt_android.view.adapter.ApplicantListAdapter
import com.example.mnt_android.view.dialog.ConfirmDialog
import com.example.mnt_android.view.dialog.NoticeDialog
import com.example.mnt_android.viewmodel.BackPressViewModel
import com.example.mnt_android.viewmodel.CreateMissionViewModel
import com.example.mnt_android.viewmodel.CreateRoomViewModel
import com.example.mnt_android.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.activity_applicant_list.*
import kotlinx.android.synthetic.main.activity_create_mission.*
import kotlinx.android.synthetic.main.activity_login.*

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