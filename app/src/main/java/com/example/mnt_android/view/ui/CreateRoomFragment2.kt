package com.example.mnt_android.view.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.mnt_android.R
import com.example.mnt_android.databinding.ActivityCreateroomBinding
import com.example.mnt_android.databinding.FragmentCreateroom2Binding
import com.example.mnt_android.viewmodel.CreateRoomViewModel

class CreateRoomFragment2 : Fragment()
{
    lateinit var createRoomViewModel: CreateRoomViewModel
    lateinit var binding: FragmentCreateroom2Binding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_createroom2,container,false)

        createRoomViewModel = (activity as CreateRoomActivity).createRoomViewModel


        binding.createRoomViewModel=createRoomViewModel
        binding.createRoomActivity=(activity as CreateRoomActivity)

        Toast.makeText(container?.context,"${createRoomViewModel.roomInfo.num.value} / ${createRoomViewModel.roomInfo.name.value}",Toast.LENGTH_LONG).show()

        return binding.root
        // return inflater.inflate(R.layout.activity_createroom2,container,false)
    }




}