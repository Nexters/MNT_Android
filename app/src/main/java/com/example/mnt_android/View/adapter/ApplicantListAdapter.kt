package com.example.mnt_android.View.adapter

import android.view.View
import com.example.mnt_android.R
import com.example.mnt_android.View.viewholder.ApplicantListViewHolder
import com.example.mnt_android.base.BaseAdapter

private val applicantList = arrayListOf("김대경", "김소영", "구예희", "최민섭", "한지혜", "지현우", "박지혜", "진유진")

class ApplicantListAdapter : BaseAdapter() {
    override val layoutId: Int
        get() = R.layout.item_applicant

    init {
//        TODO("api 연결되면 정확한 데이터 넘겨주기")
        setList(applicantList)
    }

    override fun viewHolder(layout: Int, view: View) = ApplicantListViewHolder(view)
}
