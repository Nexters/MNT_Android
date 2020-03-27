package com.example.mnt_android.view.adapter

import android.view.View
import com.example.mnt_android.R
import com.example.mnt_android.base.BaseAdapter
import com.example.mnt_android.service.model.Applicant
import com.example.mnt_android.view.viewholder.SelectManitoListViewHolder

class SelectManitoListAdapter : BaseAdapter<Applicant>() {
    override val layoutId: Int
        get() = R.layout.item_participant

    override fun viewHolder(layout: Int, view: View) = SelectManitoListViewHolder(view)
}