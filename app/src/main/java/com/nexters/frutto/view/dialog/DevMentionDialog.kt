package com.nexters.frutto.view.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.nexters.frutto.R
import kotlinx.android.synthetic.main.dialog_dev_mention.view.*
import kotlinx.android.synthetic.main.dialog_notice.view.close_btn
import kotlinx.android.synthetic.main.dialog_notice.view.title_tv

class DevMentionDialog(private val titleMsg: String, private val mentionMsg: String,
                       private val nameMsg: String) : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {


        return activity?.let {
            val view = it.layoutInflater.inflate(R.layout.dialog_dev_mention, null).apply {
                title_tv.text = titleMsg
                dev_mention.text = mentionMsg
                dev_name.text = nameMsg
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