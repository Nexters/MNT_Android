package com.example.mnt_android.view.viewholder

import android.view.View
import com.bumptech.glide.Glide
import com.example.mnt_android.base.BaseViewHolder
import kotlinx.android.synthetic.main.item_content.view.*

class ContentListViewHolder(view: View, private val isManager: Boolean) : BaseViewHolder(view) {
    override fun onBind(data: Any) {
        val content = data as Array<String>
        itemView.run {
            if(isManager) {
                naeto_tv.text = content[0]
            }

            Glide.with(context)
                .load(content[2])
                .into(image_iv)
            nito_tv.text = content[1]
            content_tv.text = content[3]
            mission_type_tv.text = content[4]
        }
    }
}