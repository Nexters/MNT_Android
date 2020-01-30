package com.example.mnt_android.View.viewholder

import android.view.View
import androidx.fragment.app.FragmentActivity
import com.example.mnt_android.base.BaseViewHolder
import com.example.mnt_android.View.dialog.CustomDialog
import kotlinx.android.synthetic.main.item_applicant.view.*

class ApplicantListViewHolder(view: View) : BaseViewHolder(view) {

    companion object {
        private val TAG = "ApplicantList Dialog"
    }

    override fun onBind(data: Any) {
        val applicant = data as String
        itemView.run {
            applicant_name_tv.text = applicant
            exit_iv.setOnClickListener {
                val supportFragmentManager = (context as FragmentActivity).supportFragmentManager
                CustomDialog(applicant).show(supportFragmentManager, TAG)
            }
        }
    }
}