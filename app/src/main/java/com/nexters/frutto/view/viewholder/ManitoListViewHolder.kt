package com.nexters.frutto.view.viewholder

import android.view.View
import com.nexters.frutto.base.BaseViewHolder
import com.nexters.frutto.binding.setFaceProfileSrc
import com.nexters.frutto.binding.setFruitProfileSrc
import com.nexters.frutto.service.model.Applicant
import com.nexters.frutto.util.getFruttoData
import kotlinx.android.synthetic.main.item_manito.view.*

class ManitoListViewHolder(view: View, private val isPublic: Boolean) : BaseViewHolder(view) {
    override fun onBind(position: Int, data: Any) {
        val applicant = data as Applicant
        itemView.run {
            setFruitProfileSrc(naeto_iv, applicant.userFruttoId)
            setFaceProfileSrc(nito_iv, applicant.manitto.fruttoId)
            naeto_tv.text =
                if (isPublic) applicant.user.name
                else getFruttoData(
                    context,
                    applicant.userFruttoId
                )?.koreanNickName
            nito_tv.text = applicant.manitto.name
        }
    }
}