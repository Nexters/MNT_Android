package com.example.mnt_android.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.mnt_android.R
import com.example.mnt_android.databinding.FragmentJoinroom2Binding
import com.example.mnt_android.viewmodel.JoinRoomViewModel

class JoinRoomFragment2 : Fragment()
{
    lateinit var joinRoomViewModel: JoinRoomViewModel
    lateinit var binding: FragmentJoinroom2Binding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_joinroom2,container,false)

        joinRoomViewModel=(activity as JoinRoomActivity).joinRoomViewModel

        binding.joinRoomViewModel = (activity as JoinRoomActivity).joinRoomViewModel
        binding.joinRoomActivity=(activity as JoinRoomActivity)

        return binding.root


    }
}