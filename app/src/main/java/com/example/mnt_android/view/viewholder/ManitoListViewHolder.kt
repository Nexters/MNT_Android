package com.example.mnt_android.view.viewholder

import android.view.View
import com.example.mnt_android.base.BaseViewHolder
import com.example.mnt_android.binding.setFaceProfileSrc
import com.example.mnt_android.binding.setFruitProfileSrc
import com.example.mnt_android.service.model.Applicant
import com.example.mnt_android.util.getFruttoData
import kotlinx.android.synthetic.main.item_manito.view.*

class ManitoListViewHolder(view: View, private val isManager: Boolean) : BaseViewHolder(view) {
    override fun onBind(position: Int, data: Any) {
        val applicant = data as Applicant
        itemView.run {
            setFruitProfileSrc(naeto_iv, applicant.userFruttoId)
            setFaceProfileSrc(nito_iv, applicant.manitto.fruttoId)
            naeto_tv.text =
                if (isManager) applicant.user.name
                else getFruttoData(
                    context,
                    applicant.userFruttoId
                )?.koreanNickName
            nito_tv.text = applicant.manitto.name
        }
    }
}