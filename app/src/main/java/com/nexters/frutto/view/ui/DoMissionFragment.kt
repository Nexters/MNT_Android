package com.nexters.frutto.view.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.nexters.frutto.R
import com.nexters.frutto.databinding.FragmentDoMission1Binding
import com.nexters.frutto.viewmodel.DoMissionViewModel
import kotlinx.android.synthetic.main.fragment_do_mission1.*
import org.jetbrains.anko.sdk21.listeners.onClick

class DoMissionFragment : Fragment()
{
    lateinit var doMissionViewModel: DoMissionViewModel
    lateinit var binding: FragmentDoMission1Binding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_do_mission1, container, false)
        return binding.root
    }

    @SuppressLint("ResourceType")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let{
            doMissionViewModel=(activity as DoMissionActivity).doMissionViewModel
            binding.doMissionActivity = activity as DoMissionActivity
            binding.doMissionViewModel = doMissionViewModel

            binding.lifecycleOwner=this


        }

        doMissionViewModel.nowUserMission.observe(this, Observer {

            if(it.isAbleImg==0)
                    linear_image_domission1.visibility=View.GONE
                else
                    linear_image_domission1.visibility=View.VISIBLE

        })

        doMissionViewModel.missionText.observe(this, Observer {
            if(it=="")
            {
                if(doMissionViewModel.imageButtonText.value!=getString(R.string.tv_get_image))
                {
                    tv_domission_domission1.setBackgroundResource(R.drawable.button_hot_pink)
                }
                else
                {
                    tv_domission_domission1.setBackgroundResource(R.drawable.button_disable)
                }

            }
            else
            {
                tv_domission_domission1.setBackgroundResource(R.drawable.button_hot_pink)
            }
        })

        doMissionViewModel.imageButtonText.observe(this, Observer {
            if(it!=getString(R.string.tv_get_image))
            {
                tv_domission_domission1.setBackgroundResource(R.drawable.button_hot_pink)
            }
            else
            {
                if(doMissionViewModel.missionText.value=="")
                {
                    tv_domission_domission1.setBackgroundResource(R.drawable.button_disable)
                }
                else
                {
                    tv_domission_domission1.setBackgroundResource(R.drawable.button_hot_pink)
                }

            }
        })

    }
}