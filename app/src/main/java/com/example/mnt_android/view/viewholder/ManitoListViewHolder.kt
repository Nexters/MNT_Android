package com.example.mnt_android.view.viewholder

import android.view.View
import com.example.mnt_android.base.BaseViewHolder
import com.example.mnt_android.binding.setFaceProfileSrc
import com.example.mnt_android.binding.setFruitProfileSrc
import com.example.mnt_android.binding.setNickName
import com.example.mnt_android.service.model.Applicant
import kotlinx.android.synthetic.main.item_manito.view.*

class ManitoListViewHolder(view: View, private val isManager: Boolean) :  BaseViewHolder(view) {
    override fun onBind(data: Any) {
        val applicant = data as Applicant
        itemView.run {
            val naetoName = if(isManager) applicant.user.name else applicant.userFruttoId.toString()
            setFruitProfileSrc(naeto_iv, applicant.userFruttoId)
            setFaceProfileSrc(nito_iv, applicant.manitto.fruttoId)
            setNickName(naeto_tv, naetoName, isManager)
            nito_tv.text = applicant.manitto.name
        }
    }
}