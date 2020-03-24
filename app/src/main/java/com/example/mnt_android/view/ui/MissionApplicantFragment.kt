package com.example.mnt_android.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mnt_android.R
import com.example.mnt_android.base.BaseFragment
import com.example.mnt_android.databinding.FragmentMissionApplicantBinding
import com.example.mnt_android.view.adapter.DoneMissionListAdapter
import com.example.mnt_android.view.adapter.NotDoneMissionListAdapter
import com.example.mnt_android.viewmodel.GameViewModel
import kotlinx.android.synthetic.main.fragment_mission_applicant.*
import org.koin.android.viewmodel.ext.android.viewModel

class MissionApplicantFragment(private val userId: String, private val roomId: Long) :
    BaseFragment() {
    private val viewModel by viewModel<GameViewModel>()
    private lateinit var binding: FragmentMissionApplicantBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = bind(inflater, container, R.layout.fragment_mission_applicant)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel.apply {
            getUserMission(userId, roomId)
        }
        return binding.root
    }

    override fun initializeUI() {
        rv_mission_not_done.run {
            layoutManager = LinearLayoutManager(context)
            adapter = NotDoneMissionListAdapter()
        }
        rv_mission_done.run {
            layoutManager = LinearLayoutManager(context)
            adapter = DoneMissionListAdapter()
        }
    }

    override fun onResume() {
        super.onResume()

        viewModel.getUserMission(userId, roomId)

    }

}