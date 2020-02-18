package com.example.mnt_android.view.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
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





        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.let {
            createRoomViewModel=(activity as CreateRoomActivity).createRoomViewModel
            binding.createRoomViewModel = (activity as CreateRoomActivity).createRoomViewModel
            binding.createRoomActivity=(activity as CreateRoomActivity)
            binding.lifecycleOwner=this
        }

    }




}