package com.example.mnt_android.view.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.mnt_android.R
import com.example.mnt_android.databinding.ActivityCreateroomBinding
import com.example.mnt_android.databinding.ActivityLoginBinding
import com.example.mnt_android.databinding.FragmentCreateroom1Binding
import com.example.mnt_android.viewmodel.CreateRoomViewModel
import com.example.mnt_android.viewmodel.LoginViewModel
import java.util.*

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