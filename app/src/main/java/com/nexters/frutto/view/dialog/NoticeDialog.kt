package com.nexters.frutto.view.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.nexters.frutto.R
import kotlinx.android.synthetic.main.dialog_notice.view.*

class NoticeDialog(private val titleMsg: String, private val explanationMsg: String) : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val view = it.layoutInflater.inflate(R.layout.dialog_notice, null).apply {
                title_tv.text = titleMsg
                explanation_tv.text = explanationMsg
                close_btn.setOnClickListener { dismiss() }
            }
            AlertDialog.Builder(it).apply {
                setView(view)
            }.create().apply {
                window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            }
        } ?: throw IllegalAccessException("Activity cannot be null")
    }
}