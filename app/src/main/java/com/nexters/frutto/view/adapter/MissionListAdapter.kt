package com.nexters.frutto.view.adapter

import android.view.View
import com.nexters.frutto.R
import com.nexters.frutto.base.BaseAdapter
import com.nexters.frutto.view.viewholder.MissionListViewHolder

private val applicantList = arrayListOf("칭찬하기","응원하기","3행시","닮은꼴찍어보내기")

class MissionListAdapter : BaseAdapter<String>() {
    override val layoutId: Int
        get() = R.layout.item_applicant

    init {
//        TODO("api 연결되면 정확한 데이터 넘겨주기")
        setList(applicantList)
    }

    override fun viewHolder(layout: Int, view: View) = MissionListViewHolder(view)
}
