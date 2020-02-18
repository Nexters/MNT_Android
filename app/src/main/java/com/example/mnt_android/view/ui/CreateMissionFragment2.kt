package com.example.mnt_android.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.mnt_android.R
import com.example.mnt_android.databinding.FragmentCreatemission1Binding
import com.example.mnt_android.viewmodel.CreateMissionViewModel

class CreateMissionFragment2  : Fragment()
{

    lateinit var createMissionViewModel: CreateMissionViewModel
    lateinit var binding: FragmentCreatemission1Binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_createmission2,container,false)

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
    }
}