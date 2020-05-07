package com.nexters.frutto.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.nexters.frutto.R
import com.nexters.frutto.base.BaseFragment
import com.nexters.frutto.databinding.FragmentMissionApplicantBinding
import com.nexters.frutto.view.adapter.DoneMissionListAdapter
import com.nexters.frutto.view.adapter.NotDoneMissionListAdapter
import com.nexters.frutto.viewmodel.GameViewModel
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