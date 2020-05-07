package com.nexters.frutto.view.viewholder

import android.view.View
import com.nexters.frutto.base.BaseViewHolder
import com.nexters.frutto.binding.setFaceProfileSrc
import com.nexters.frutto.service.model.Applicant
import kotlinx.android.synthetic.main.item_participant.view.*

class SelectManitoListViewHolder(view: View, private val onClick: (String?, View?) -> Unit) :
    BaseViewHolder(view) {
    override fun onBind(position: Int, data: Any) {
        val content = data as Applicant
        content.run {
            itemView.run {
                setFaceProfileSrc(profile_iv, content.userFruttoId)
                name_tv.text = content.user.name
                setOnClickListener {
                    onClick(content.manitto.id, this)
                }
            }
        }
    }
}