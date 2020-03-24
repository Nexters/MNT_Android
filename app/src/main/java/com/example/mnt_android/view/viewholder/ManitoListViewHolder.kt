package com.example.mnt_android.view.viewholder

import android.view.View
import com.example.mnt_android.base.BaseViewHolder
import com.example.mnt_android.service.model.Applicant
import com.example.mnt_android.util.getFruttoData
import kotlinx.android.synthetic.main.item_manito.view.*
import org.jetbrains.anko.image

class ManitoListViewHolder(view: View, private val isManager: Boolean) :  BaseViewHolder(view) {
    override fun onBind(data: Any) {
        val applicant = data as Applicant
        itemView.run {
            val naetoData = getFruttoData(context, applicant.userFruttoId+1)
            val naetoImgRes = resources.getIdentifier(
                "img_profile_${naetoData?.englishName}",
                "drawable",
                context?.packageName
            )
            val nitoData = getFruttoData(context, applicant.manitto.fruttoId ?: -1)
            val nitoImgRes = resources.getIdentifier(
                "img_profile_face_${"%02d".format(nitoData?.id)}",
                "drawable",
                context?.packageName
            )

            naeto_iv.image = resources.getDrawable(naetoImgRes, null)
            naeto_tv.text = if(isManager) applicant.user.name else naetoData?.koreanNickName
            nito_iv.image = resources.getDrawable(nitoImgRes, null)
            nito_tv.text = applicant.manitto.name
        }
    }
}