package com.example.mnt_android.view.adapter

import android.view.View
import com.example.mnt_android.R
import com.example.mnt_android.base.BaseAdapter
import com.example.mnt_android.service.model.Applicant
import com.example.mnt_android.view.viewholder.ManitoListViewHolder

class ManitoListAdapter : BaseAdapter<Applicant>() {
    override val layoutId: Int
        get() = R.layout.item_manito
    var isPublic = false

    override fun viewHolder(layout: Int, view: View) = ManitoListViewHolder(view, isPublic)

}