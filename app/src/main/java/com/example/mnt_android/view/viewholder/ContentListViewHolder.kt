package com.example.mnt_android.view.viewholder

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.mnt_android.base.BaseViewHolder
import com.example.mnt_android.extension.checkUploadDate
import com.example.mnt_android.service.model.UserMissionResponse
import com.example.mnt_android.util.TAG_IS_MANAGER
import com.example.mnt_android.util.getFruttoData
import com.example.mnt_android.view.ui.MissionDetailActivity
import kotlinx.android.synthetic.main.item_content.view.*

class ContentListViewHolder(view: View, private val isManager: Boolean) : BaseViewHolder(view) {
    override fun onBind(data: Any) {
        val content = data as UserMissionResponse
        content.run {
            itemView.run {
                if (userMission.missionImg != null) {
                    setContentImg(context, userMission.missionImg!!, image_iv)
                } else {
                    image_iv.visibility = View.GONE
                }
                setFruitImg(context, userFruttoId, naeto_iv)
                setFaceImg(context, manitto?.fruttoId, nito_iv)
                naeto_tv.text = when (isManager) {
                    true -> userMission.user.name
                    false -> userFruttoId.toString()
                }
                nito_tv.text = manitto?.name
                mission_type_tv.text = missionName
                content_tv.text = userMission.content
                day_tv.text = userMission.userDoneTime?.checkUploadDate
                setOnClickListener {
                    val intent = Intent(context, MissionDetailActivity::class.java)
                    intent.putExtra(TAG_IS_MANAGER, isManager)
                    intent.putExtra(MissionDetailActivity.TAG_MISSION, content)
                    context.startActivity(intent)
                }
            }
        }
    }

    private fun setContentImg(context: Context, url: String, iv: ImageView) {
        Glide.with(context)
            .load(url)
            .into(iv)
    }

    private fun setFruitImg(context: Context, imgId: Int?, iv: ImageView) {
        val imgNm = getFruttoData(context, (imgId?:-1)+1)?.englishName
        val imgRes = context.resources.getIdentifier(
            "img_profile_chat_${imgNm}",
            "drawable",
            context.packageName
        )
        iv.setImageResource(imgRes)
    }

    private fun setFaceImg(context: Context, imgId: Int?, iv: ImageView) {
        val imgRes = context.resources.getIdentifier(
            "img_profile_face_${"%02d".format(imgId)}",
            "drawable",
            context.packageName
        )
        iv.setImageResource(imgRes)
    }
}