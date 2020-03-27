package com.example.mnt_android.view.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mnt_android.R
import com.example.mnt_android.base.BaseFragment
import com.example.mnt_android.databinding.FragmentCreateroom1Binding
import com.example.mnt_android.databinding.FragmentMissionManagerBinding
import com.example.mnt_android.view.adapter.ApplicantListAdapter
import com.example.mnt_android.view.adapter.MissionListAdapter
import com.example.mnt_android.view.adapter.MissionManagerListAdapter
import com.example.mnt_android.viewmodel.CreateRoomViewModel
import com.example.mnt_android.viewmodel.GameViewModel
import com.example.mnt_android.viewmodel.JoinRoomViewModel
import kotlinx.android.synthetic.main.activity_applicant_list.*
import kotlinx.android.synthetic.main.fragment_mission_manager.*

class MissionManagerFragment : Fragment()
{
    lateinit var binding: FragmentMissionManagerBinding
    lateinit var gameViewModel : GameViewModel
   val  adap : MissionManagerListAdapter = MissionManagerListAdapter()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_mission_manager,container,false)


        binding.gameActivity = activity as GameActivity



        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.let {
            gameViewModel=(activity as GameActivity).gameViewModel

            binding.gameActivity = (activity as GameActivity)
            binding.gameViewModel = gameViewModel
            binding.lifecycleOwner=this

            rv_mission_manager.apply {
                layoutManager = LinearLayoutManager((activity as GameActivity))
                adapter = adap
            }

            gameViewModel.changeManagerList.observe(this, Observer {
                if(it) {
                    Log.d("wlgusdnzzz", "missionManager Set")
                    adap.setList(gameViewModel.missionManager)
                }
            })

        }

    }

    override fun onResume() {
        super.onResume()
        gameViewModel.groupByMission( (activity as GameActivity).sharedPreferences.getLong("roomId", 0))
    }

}