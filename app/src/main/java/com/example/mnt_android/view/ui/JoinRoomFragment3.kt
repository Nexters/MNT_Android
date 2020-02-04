package com.example.mnt_android.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.mnt_android.R
import com.example.mnt_android.databinding.FragmentJoinroom3Binding
import com.example.mnt_android.viewmodel.JoinRoomViewModel

class JoinRoomFragment3 : Fragment()
{
    lateinit var joinRoomViewModel: JoinRoomViewModel
    lateinit var binding: FragmentJoinroom3Binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_joinroom3,container,false)

        joinRoomViewModel=(activity as JoinRoomActivity).joinRoomViewModel

        binding.joinRoomViewModel = (activity as JoinRoomActivity).joinRoomViewModel



        return binding.root


    }
}