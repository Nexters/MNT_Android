package com.example.mnt_android.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.mnt_android.R
import com.example.mnt_android.databinding.FragmentDoMission1Binding
import com.example.mnt_android.viewmodel.CreateMissionViewModel
import com.example.mnt_android.viewmodel.DoMissionViewModel

class DoMissionFragment : Fragment()
{
    lateinit var doMissionViewModel: DoMissionViewModel
    lateinit var binding: FragmentDoMission1Binding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_do_mission1,container,false)




        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let{
            doMissionViewModel=(activity as DoMissionActivity).doMissionViewModel
            binding.doMissionActivity = activity as DoMissionActivity
            binding.doMissionViewModel = doMissionViewModel

            binding.lifecycleOwner=this
        }

    }
}