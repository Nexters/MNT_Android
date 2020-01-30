package com.example.mnt_android.View.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.mnt_android.R
import com.example.mnt_android.databinding.FragmentCreateroom1Binding
import com.example.mnt_android.viewmodel.CreateRoomViewModel

class CreateRoomFragment : Fragment()
{
    lateinit var createRoomViewModel: CreateRoomViewModel
    lateinit var binding: FragmentCreateroom1Binding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_createroom1,container,false)

       createRoomViewModel=(activity as CreateRoomActivity).createRoomViewModel
        binding.createRoomViewModel = (activity as CreateRoomActivity).createRoomViewModel
        binding.createRoomActivity=(activity as CreateRoomActivity)



        return binding.root


    }




}