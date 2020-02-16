package com.example.mnt_android.view.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mnt_android.R
import com.example.mnt_android.base.BaseFragment
import com.example.mnt_android.databinding.FragmentDashBoardBinding
import com.example.mnt_android.util.TAG_IS_MANAGER
import com.example.mnt_android.view.adapter.ContentListAdapter

class DashBoardFragment(private val isManager: Boolean = false) :
    BaseFragment() {
    private lateinit var binding : FragmentDashBoardBinding

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
