package com.nexters.frutto.view.viewholder

import android.view.View
import com.nexters.frutto.base.BaseViewHolder
import kotlinx.android.synthetic.main.item_mission_manager.view.*

class MissionManagerListViewHolder(view: View, private val isManager: Boolean) :  BaseViewHolder(view) {
    override fun onBind(position: Int, data: Any) {
        val mission = data as Pair<String,String>
        itemView.run {

           tv_mission_name_manager.text = mission.first
            tv_mission_done_num_manager.text = mission.second

        }
    }
}