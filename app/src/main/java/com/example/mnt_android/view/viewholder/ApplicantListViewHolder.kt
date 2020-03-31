package com.example.mnt_android.view.viewholder

import android.view.View
import androidx.fragment.app.FragmentActivity
import com.example.mnt_android.base.BaseViewHolder
import com.example.mnt_android.binding.setFaceProfileSrc
import com.example.mnt_android.binding.setFruitProfileSrc
import com.example.mnt_android.extension.isFalse
import com.example.mnt_android.extension.isTrue
import com.example.mnt_android.service.model.Applicant
import com.example.mnt_android.view.dialog.ConfirmDialog
import kotlinx.android.synthetic.main.item_applicant.view.*

class ApplicantListViewHolder(
    view: View,
    private val isManager: Int,
    private val onExitApplicant: (Long, String) -> Unit
) : BaseViewHolder(view) {

    companion object {
        private const val TAG = "ApplicantList Dialog"
    }

    override fun onBind(position: Int, data: Any) {
        val applicant = data as Applicant
        itemView.run {
            if (applicant.isCreater.isTrue) {
                setFruitProfileSrc(profile_iv, 0)
            } else {
                setFaceProfileSrc(profile_iv, position)
            }
            applicant_name_tv.text = applicant.user.name
            exit_btn.setOnClickListener {
                val supportFragmentManager = (context as FragmentActivity).supportFragmentManager
                ConfirmDialog(
                    "${applicant.user.name}님을 내보내시겠습니까?",
                    "취소",
                    "내보내기"
                ) {
                    onExitApplicant(applicant.room.id.toLong(), applicant.user.id)
                }.show(supportFragmentManager, TAG)
            }

            if (isManager == 1 && applicant.isCreater.isFalse) {
                exit_btn.visibility = View.VISIBLE
            }
        }
    }
}