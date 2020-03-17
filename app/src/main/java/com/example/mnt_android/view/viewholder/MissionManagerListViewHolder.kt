package com.example.mnt_android.view.viewholder

import android.view.View
import com.example.mnt_android.base.BaseViewHolder
import com.example.mnt_android.service.model.Applicant
import com.example.mnt_android.service.model.UserMissionResponse
import kotlinx.android.synthetic.main.item_manito.view.*
import kotlinx.android.synthetic.main.item_mission_manager.view.*

class MissionManagerListViewHolder(view: View, private val isManager: Boolean) :  BaseViewHolder(view) {
    override fun onBind(data: Any) {
        val mission = data as Pair<String,String>
        itemView.run {

           tv_mission_name_manager.text = mission.first
            tv_mission_done_num_manager.text = mission.second

        }
    }
}