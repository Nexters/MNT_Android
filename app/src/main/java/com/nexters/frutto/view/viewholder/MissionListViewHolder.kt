package com.nexters.frutto.view.viewholder

import android.view.View
import androidx.fragment.app.FragmentActivity
import com.nexters.frutto.base.BaseViewHolder
import com.nexters.frutto.view.dialog.ConfirmDialog
import kotlinx.android.synthetic.main.item_applicant.view.*

class MissionListViewHolder(view: View) : BaseViewHolder(view) {

    companion object {
        private val TAG = "Mission Dialog"
    }

    override fun onBind(position: Int, data: Any) {
        val applicant = data as String
        itemView.run {
            applicant_name_tv.text = applicant
            exit_btn.setOnClickListener {
                val supportFragmentManager = (context as FragmentActivity).supportFragmentManager
                ConfirmDialog(applicant).show(supportFragmentManager, TAG)
            }
        }
    }
}