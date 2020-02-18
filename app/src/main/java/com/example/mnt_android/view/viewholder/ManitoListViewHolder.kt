package com.example.mnt_android.view.viewholder

import android.view.View
import com.example.mnt_android.base.BaseViewHolder
import com.example.mnt_android.service.model.Applicant
import kotlinx.android.synthetic.main.item_manito.view.*

class ManitoListViewHolder(view: View, private val isManager: Boolean) :  BaseViewHolder(view) {
    override fun onBind(data: Any) {
        val applicant = data as Applicant
        itemView.run {
            naeto_tv.text = applicant.user.name
            nito_tv.text = applicant.manittoId
        }
    }
}