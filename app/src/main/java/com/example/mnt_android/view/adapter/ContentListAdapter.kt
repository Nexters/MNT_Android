package com.example.mnt_android.view.adapter

import android.view.View
import com.example.mnt_android.R
import com.example.mnt_android.base.BaseAdapter
import com.example.mnt_android.service.model.UserMissionResponse
import com.example.mnt_android.view.viewholder.ContentListViewHolder

class ContentListAdapter : BaseAdapter<UserMissionResponse>() {
    override val layoutId: Int
        get() = R.layout.item_content
    var isPublic: Boolean = false

    override fun viewHolder(layout: Int, view: View) = ContentListViewHolder(view, isPublic)
}