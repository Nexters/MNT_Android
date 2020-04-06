package com.example.mnt_android.view.ui

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mnt_android.R
import com.example.mnt_android.base.BaseFragment
import com.example.mnt_android.databinding.FragmentMissionApplicantBinding
import com.example.mnt_android.view.adapter.DoneMissionListAdapter
import com.example.mnt_android.view.adapter.NotDoneMissionListAdapter
import com.example.mnt_android.viewmodel.GameViewModel
import kotlinx.android.synthetic.main.fragment_mission_applicant.*

class MissionApplicantFragment : BaseFragment() {
    lateinit var viewModel : GameViewModel
    private lateinit var binding: FragmentMissionApplicantBinding
    var notDoneAdapter = NotDoneMissionListAdapter()
    var doneAdapter = DoneMissionListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = bind(inflater, container, R.layout.fragment_mission_applicant)



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let {

            binding.lifecycleOwner = this
            viewModel = (activity as GameActivity).gameViewModel

            rv_mission_not_done.run {
                layoutManager = LinearLayoutManager(context)
                adapter = notDoneAdapter
            }
            rv_mission_done.run {
                layoutManager = LinearLayoutManager(context)
                adapter = doneAdapter
            }

            viewModel.listIsDone.observe(this, Observer {
                if(it)
                {
                    notDoneAdapter.setList(viewModel.notDoneUserMissions)
                    doneAdapter.setList(viewModel.doneUserMissions)
                }
            })

        }

    }

    override fun initializeUI() {

    }

    override fun onResume() {
        super.onResume()

        viewModel.getUserMission(
            (activity as GameActivity).sharedPreferences.getString("kakao_token", ""),
            (activity as GameActivity).sharedPreferences.getLong("roomId", 0)
        )



    }

}