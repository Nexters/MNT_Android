package com.example.mnt_android.service.model.dialog

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.mnt_android.R
import kotlinx.android.synthetic.main.custom_dialog.view.*

class CustomDialog(
    private val questionMsg: String,
    private val cancelMsg: String? = "cancel",
    private val confirmMsg: String? = "confirm",
    private val confirmOnClickListener: () -> Unit = {}
) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val view = it.layoutInflater.inflate(R.layout.custom_dialog, null).apply {
                question_tv.text = questionMsg
                confirm_btn.run {
                    text = confirmMsg
                    setOnClickListener { confirmOnClickListener }
                }
                cancel_btn.run {
                    text = cancelMsg
                    setOnClickListener { dismiss() }
                }
            }

            AlertDialog.Builder(it).apply {
                setView(view)
            }.create().apply {
                window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            }
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}