package com.example.mnt_android.view.adapter

import android.view.View
import com.example.mnt_android.R
import com.example.mnt_android.view.viewholder.ApplicantListViewHolder
import com.example.mnt_android.base.BaseAdapter
import com.example.mnt_android.service.model.Applicant

class ApplicantListAdapter(private val onExitApplicant: (Long, String) -> Unit) : BaseAdapter<Applicant>() {
    override val layoutId: Int
        get() = R.layout.item_applicant

    var isManager: Int = 0

    override fun viewHolder(layout: Int, view: View) = ApplicantListViewHolder(view, isManager, onExitApplicant)
}
