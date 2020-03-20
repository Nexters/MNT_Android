package com.example.mnt_android.view.viewholder

import android.view.View
import com.example.mnt_android.base.BaseViewHolder
import com.example.mnt_android.service.model.UserMissionResponse
import kotlinx.android.synthetic.main.item_mission_notdone_applicant.view.*

class NotDoneMissionListViewHolder(view: View) : BaseViewHolder(view) {
    override fun onBind(data: Any) {
        val content = data as UserMissionResponse
        content.run {
            itemView.run {
                tv_mission_name_not_done_applicant.text = content.missionName
                not_done_layout.setOnClickListener {
                    // TODO : 터치 후 구현 필요
                }
            }
        }
    }
}