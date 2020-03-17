package com.example.mnt_android.view.adapter

import android.view.View
import com.example.mnt_android.R
import com.example.mnt_android.base.BaseAdapter
import com.example.mnt_android.service.model.Applicant
import com.example.mnt_android.service.model.UserMissionResponse
import com.example.mnt_android.view.viewholder.ManitoListViewHolder
import com.example.mnt_android.view.viewholder.MissionManagerListViewHolder

class MissionManagerListAdapter : BaseAdapter<Pair<String,String>>() {
    override val layoutId: Int
        get() = R.layout.item_mission_manager
    var isManager = true

    override fun viewHolder(layout: Int, view: View) = MissionManagerListViewHolder(view, isManager)

}