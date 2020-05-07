package com.nexters.frutto.view.adapter

import android.view.View
import com.nexters.frutto.R
import com.nexters.frutto.view.viewholder.ApplicantListViewHolder
import com.nexters.frutto.base.BaseAdapter
import com.nexters.frutto.service.model.Applicant

class ApplicantListAdapter(private val onExitApplicant: (Long, String) -> Unit) : BaseAdapter<Applicant>() {
    override val layoutId: Int
        get() = R.layout.item_applicant

    var isManager: Int = 0

    override fun viewHolder(layout: Int, view: View) = ApplicantListViewHolder(view, isManager, onExitApplicant)
}
