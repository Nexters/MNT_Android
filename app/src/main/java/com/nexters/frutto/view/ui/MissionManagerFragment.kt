package com.nexters.frutto.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.nexters.frutto.R
import com.nexters.frutto.databinding.FragmentCreateroom1Binding
import com.nexters.frutto.databinding.FragmentMissionManagerBinding
import com.nexters.frutto.view.adapter.MissionManagerListAdapter
import com.nexters.frutto.viewmodel.GameViewModel
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