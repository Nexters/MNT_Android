package com.example.mnt_android.view.adapter

import android.view.View
import com.example.mnt_android.R
import com.example.mnt_android.base.BaseAdapter
import com.example.mnt_android.service.model.UserMissionResponse
import com.example.mnt_android.view.viewholder.DoneMissionListViewHolder

class DoneMissionListAdapter : BaseAdapter<UserMissionResponse>() {
    override val layoutId: Int
        get() = R.layout.item_mission_done_applicant

    override fun viewHolder(layout: Int, view: View) = DoneMissionListViewHolder(view)
}