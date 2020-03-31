package com.example.mnt_android.view.viewholder

import android.view.View
import com.example.mnt_android.base.BaseViewHolder
import com.example.mnt_android.service.model.UserMissionResponse
import kotlinx.android.synthetic.main.item_mission_done_applicant.view.*

class DoneMissionListViewHolder(view: View) : BaseViewHolder(view) {
    override fun onBind(position: Int, data: Any) {
        val content = data as UserMissionResponse
        content.run {
            itemView.run {
                tv_mission_name_done_applicant.text = content.missionName
                done_layout.setOnClickListener {
                    // TODO : 터치 후 구현 필요
                }
            }
        }
    }
}