package com.example.mnt_android.view.viewholder

import android.view.View
import com.bumptech.glide.Glide
import com.example.mnt_android.base.BaseViewHolder
import kotlinx.android.synthetic.main.item_manito.view.*

class ManitoListViewHolder(view: View, private val isManager: Boolean) :  BaseViewHolder(view) {
    override fun onBind(data: Any) {
        val manito = data as Array<String>
        itemView.run {
            if (isManager) {
                naeto_tv.text = manito[0]
                Glide.with(this)
                    .load(manito[1])
                    .into(naeto_iv)
            }
            Glide.with(this)
                .load(manito[3])
                .into(nito_iv)
            nito_tv.text = manito[2]
        }
    }

}