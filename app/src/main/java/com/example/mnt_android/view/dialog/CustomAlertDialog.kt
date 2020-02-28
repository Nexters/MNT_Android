package com.example.mnt_android.view.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.mnt_android.R
import kotlinx.android.synthetic.main.dialog_alert.view.*

class CustomAlertDialog(
    private val dialogMsg: String, private val btnMsg: String? = "확인",
    private val confirmOnClickListener: () -> Unit = {}
) : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val view = it.layoutInflater.inflate(R.layout.dialog_alert, null).apply {
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