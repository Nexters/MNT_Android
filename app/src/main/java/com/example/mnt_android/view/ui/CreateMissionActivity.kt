package com.example.mnt_android.view.ui

import android.content.Intent
import android.os.Bundle
import android.transition.Visibility
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mnt_android.R
import com.example.mnt_android.base.BaseActivity
import com.example.mnt_android.base.BaseViewModel
import com.example.mnt_android.databinding.ActivityCreateMissionBinding
import com.example.mnt_android.databinding.ActivityLoginBinding
import com.example.mnt_android.view.adapter.ApplicantListAdapter
import com.example.mnt_android.viewmodel.CreateMissionViewModel
import com.example.mnt_android.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.activity_applicant_list.*
import kotlinx.android.synthetic.main.activity_create_mission.*
import kotlinx.android.synthetic.main.activity_login.*

class CreateMissionActivity : AppCompatActivity()
{
    lateinit var viewModel: CreateMissionViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil
            .setContentView<ActivityCreateMissionBinding>(this, R.layout.activity_create_mission)
        binding.lifecycleOwner = this // LiveData를 사용하기 위해서 없으면 Observe할때마다 refresh안딤

         viewModel = ViewModelProviders.of(this)[CreateMissionViewModel::class.java]
        binding.createMissionViewModel = viewModel
        binding.createMissionActivity=this


        var missionspinadapter = ArrayAdapter.createFromResource(this,R.array.create_mission,R.layout.spinner_item)
        missionspinadapter.setDropDownViewResource(R.layout.spinner_dropdown_item)
        spin_mission_name_create_mission.adapter=missionspinadapter


        viewModel.send.observe(this, Observer {
            if(it==true)
            {
                //sendMission한 뒤에 finish()를 하기 위함
                Toast.makeText(this,"확인 팝업 2회",Toast.LENGTH_SHORT).show()
                finish()
            }
        })




        spin_mission_name_create_mission.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

                viewModel.setMission(position)
                viewModel.writeDirect(position)
            }
        }



    }

    override fun onBackPressed() {
        finish()
    }

}