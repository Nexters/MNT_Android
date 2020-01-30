package com.example.mnt_android.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.mnt_android.R
import com.example.mnt_android.databinding.FragmentCreateroom2Binding
import com.example.mnt_android.databinding.FragmentCreateroom3Binding
import com.example.mnt_android.viewmodel.CreateRoomViewModel
import com.kakao.kakaolink.v2.KakaoLinkResponse
import com.kakao.kakaolink.v2.KakaoLinkService
import com.kakao.message.template.LinkObject
import com.kakao.message.template.TextTemplate
import com.kakao.network.ErrorResult
import com.kakao.network.callback.ResponseCallback
import java.util.HashMap

class CreateRoomFragment3 : Fragment()
{
    lateinit var createRoomViewModel: CreateRoomViewModel
    lateinit var binding: FragmentCreateroom3Binding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_createroom3,container,false)

        createRoomViewModel = (activity as CreateRoomActivity).createRoomViewModel


        binding.createRoomViewModel=createRoomViewModel
        binding.createRoomActivity = (activity as CreateRoomActivity)


        return binding.root
    }






}