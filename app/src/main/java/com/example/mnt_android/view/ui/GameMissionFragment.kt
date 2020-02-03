package com.example.mnt_android.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mnt_android.R
import com.example.mnt_android.base.BaseFragment
import com.example.mnt_android.databinding.FragmentCreateroom1Binding
import com.example.mnt_android.databinding.FragmentGameMissionBinding
import com.example.mnt_android.view.adapter.ApplicantListAdapter
import com.example.mnt_android.view.adapter.MissionListAdapter
import com.example.mnt_android.viewmodel.CreateRoomViewModel
import kotlinx.android.synthetic.main.activity_applicant_list.*
import kotlinx.android.synthetic.main.fragment_game_mission.*

class GameMissionFragment : Fragment()
{
    lateinit var binding: FragmentGameMissionBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_game_mission,container,false)


        binding.gameActivity = activity as GameActivity

        return binding.root


    }
}