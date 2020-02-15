package com.example.mnt_android.view.adapter

import android.view.View
import com.example.mnt_android.R
import com.example.mnt_android.view.viewholder.ApplicantListViewHolder
import com.example.mnt_android.base.BaseAdapter
import com.example.mnt_android.service.model.User

private val applicantList = arrayListOf("김대경", "김소영", "구예희", "최민섭", "한지혜", "지현우", "박지혜", "진유진")

class ApplicantListAdapter : BaseAdapter<User>() {
    override val layoutId: Int
        get() = R.layout.item_applicant

    override fun viewHolder(layout: Int, view: View) = ApplicantListViewHolder(view)
}
