package com.example.mnt_android.view.ui

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.example.mnt_android.R
import com.example.mnt_android.databinding.FragmentCreatemission1Binding
import com.example.mnt_android.databinding.FragmentCreateroom1Binding
import com.example.mnt_android.view.dialog.ConfirmDialog
import com.example.mnt_android.view.dialog.CustomAlertDialog
import com.example.mnt_android.view.dialog.NoticeDialog
import com.example.mnt_android.viewmodel.CreateMissionViewModel
import com.example.mnt_android.viewmodel.CreateRoomViewModel
import kotlinx.android.synthetic.main.fragment_createmission1.*

class CreateMissionFragment : Fragment()
{
    lateinit var createMissionViewModel: CreateMissionViewModel
    lateinit var binding: FragmentCreatemission1Binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_createmission1,container,false)
        return binding.root



    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.let{
            createMissionViewModel = ViewModelProviders.of(this)[CreateMissionViewModel::class.java]
            binding.createMissionViewModel = createMissionViewModel
            binding.createMissionActivity= activity as CreateMissionActivity

            binding.lifecycleOwner=this
        }

        createMissionViewModel.isCreated.observe(this, Observer {
            if(it==true)
            {
                createMissionViewModel.isCreated.observe(this, Observer {
                    if(it==true)
                    {
                        ConfirmDialog("참가자들에게 미션이 부여되며 알림이 울립니다.","취소","보내기")
                        {
                           CustomAlertDialog("참가자들에게 미션을 전달했습니다.","확인"){
                               createMissionViewModel.isCreated.value = false
                           }
                                .show(
                                    (activity as CreateMissionActivity).supportFragmentManager,
                                    "CreateMission"
                                )



                        }.show((activity as CreateMissionActivity).supportFragmentManager,"CreateMission")



                    }

                })
            }
            else
            {
                (activity as CreateMissionActivity).finish()
            }
        })


        var missionspinadapter = ArrayAdapter.createFromResource(activity, R.array.arr_create_mission, R.layout.spinner_item)
        missionspinadapter.setDropDownViewResource(R.layout.spinner_dropdown_item)
        spin_mission_name_create_mission.adapter=missionspinadapter


        spin_mission_name_create_mission.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

               createMissionViewModel.setMission(position)
            }
        }

    }

}