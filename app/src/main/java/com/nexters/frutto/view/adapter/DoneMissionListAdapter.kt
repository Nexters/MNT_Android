package com.nexters.frutto.view.adapter

import android.view.View
import com.nexters.frutto.R
import com.nexters.frutto.base.BaseAdapter
import com.nexters.frutto.service.model.UserMissionResponse
import com.nexters.frutto.view.viewholder.DoneMissionListViewHolder

class DoneMissionListAdapter : BaseAdapter<UserMissionResponse>() {
    override val layoutId: Int
        get() = R.layout.item_mission_done_applicant

    override fun viewHolder(layout: Int, view: View) = DoneMissionListViewHolder(view)
}