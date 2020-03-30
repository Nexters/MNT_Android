package com.example.mnt_android.view.viewholder

import android.view.View
import com.example.mnt_android.base.BaseViewHolder
import com.example.mnt_android.binding.setFruitProfileSrc
import com.example.mnt_android.service.model.Applicant
import kotlinx.android.synthetic.main.item_participant.view.*

class SelectManitoListViewHolder(view: View, private val onClick: (String?, View?) -> Unit) :
    BaseViewHolder(view) {
    override fun onBind(data: Any) {
        val content = data as Applicant
        content.run {
            itemView.run {
                setFruitProfileSrc(profile_iv, content.userFruttoId)
                name_tv.text = content.user.name
                setOnClickListener {
                    onClick(content.manitto.id, this)
                }
            }
        }
    }
}