package com.example.mnt_android.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.mnt_android.R
import com.example.mnt_android.viewmodel.JoinRoomViewModel
import com.example.mnt_android.databinding.FragmentJoinroom1Binding

class JoinRoomFragment : Fragment()
{
    lateinit var joinRoomViewModel: JoinRoomViewModel
    lateinit var binding: FragmentJoinroom1Binding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_joinroom1,container,false)

        joinRoomViewModel=(activity as JoinRoomActivity).joinRoomViewModel

        binding.joinRoomViewModel =joinRoomViewModel
        binding.joinRoomActivity=(activity as JoinRoomActivity)



        return binding.root


    }
}