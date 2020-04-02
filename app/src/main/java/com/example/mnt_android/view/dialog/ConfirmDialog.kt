package com.example.mnt_android.view.dialog

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.mnt_android.R
import kotlinx.android.synthetic.main.dialog_comfirm.view.*

class ConfirmDialog(
    private val questionMsg: String,
    private val cancelMsg: String? = "취소",
    private val confirmMsg: String? = "확인",
    private val confirmOnClickListener: () -> Unit = {}
) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val view = it.layoutInflater.inflate(R.layout.dialog_comfirm, null).apply {
                question_tv.text = questionMsg
                confirm_btn.run {
                    text = confirmMsg
                    setOnClickListener {
                        dismiss()
                        confirmOnClickListener()
                    }
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