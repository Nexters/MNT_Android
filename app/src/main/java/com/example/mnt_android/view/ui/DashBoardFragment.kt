package com.example.mnt_android.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mnt_android.R
import com.example.mnt_android.base.BaseFragment
import com.example.mnt_android.databinding.FragmentDashBoardBinding

class DashBoardFragment(private val isManager: Boolean) : BaseFragment() {
    lateinit var binding: FragmentDashBoardBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = bind(inflater, container, R.layout.fragment_dash_board)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun initializeUI() {
    }

}
