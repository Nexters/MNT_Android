package com.nexters.frutto.view.adapter

import android.view.View
import com.nexters.frutto.R
import com.nexters.frutto.base.BaseAdapter
import com.nexters.frutto.service.model.Applicant
import com.nexters.frutto.view.viewholder.ManitoListViewHolder

class ManitoListAdapter : BaseAdapter<Applicant>() {
    override val layoutId: Int
        get() = R.layout.item_manito
    var isPublic = false

    override fun viewHolder(layout: Int, view: View) = ManitoListViewHolder(view, isPublic)

}