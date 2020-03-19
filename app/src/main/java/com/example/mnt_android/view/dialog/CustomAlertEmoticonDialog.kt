package com.example.mnt_android.view.dialog

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.mnt_android.R
import kotlinx.android.synthetic.main.dialog_emoticon_alert.view.*

class CustomAlertEmoticonDialog(
    private val dialogEmoticon: String,
    private val dialogMsg: String,
    private val btnMsg: String? = "확인",
    private val confirmOnClickListener: () -> Unit = {}
) : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val view = it.layoutInflater.inflate(R.layout.dialog_emoticon_alert, null).apply {
                emoticon_tv.text = dialogEmoticon
                question_tv.text = dialogMsg
                confirm_btn.run {
                    text = btnMsg
                    setOnClickListener {
                        confirmOnClickListener()
                        dismiss()
                    }
                }
            }
            AlertDialog.Builder(it).apply {
                setView(view)
            }.create().apply {
                window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            }
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}