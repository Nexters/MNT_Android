package com.example.mnt_android.view.viewholder

import android.view.View
import androidx.fragment.app.FragmentActivity
import com.example.mnt_android.base.BaseViewHolder
import com.example.mnt_android.service.model.Applicant
import com.example.mnt_android.service.model.dialog.CustomDialog
import com.example.mnt_android.util.FALSE
import kotlinx.android.synthetic.main.item_applicant.view.*

class ApplicantListViewHolder(view: View, private val isManager: Int) : BaseViewHolder(view) {

    companion object {
        private val TAG = "ApplicantList Dialog"
    }

    override fun onBind(data: Any) {
        val applicant = data as Applicant
        itemView.run {
            applicant_name_tv.text = applicant.user.name
            exit_btn.setOnClickListener {
                val supportFragmentManager = (context as FragmentActivity).supportFragmentManager
                CustomDialog(applicant.user.name).show(supportFragmentManager, TAG)
            }

            if (isManager == 1 && applicant.isCreater == FALSE) {
                exit_btn.visibility = View.VISIBLE
            }
        }
    }
}