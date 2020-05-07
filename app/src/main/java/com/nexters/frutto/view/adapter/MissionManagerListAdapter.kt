package com.nexters.frutto.view.adapter

import android.view.View
import com.nexters.frutto.R
import com.nexters.frutto.base.BaseAdapter
import com.nexters.frutto.view.viewholder.MissionManagerListViewHolder

class MissionManagerListAdapter : BaseAdapter<Pair<String,String>>() {
    override val layoutId: Int
        get() = R.layout.item_mission_manager
    var isManager = true

    override fun viewHolder(layout: Int, view: View) = MissionManagerListViewHolder(view, isManager)

}