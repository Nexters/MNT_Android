package com.example.mnt_android.view.dialog

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.mnt_android.R
import com.example.mnt_android.binding.setPopupEndFruit
import kotlinx.android.synthetic.main.dialog_notice_manito.view.*

class NoticeManitoDialog(
    private val manitoFruttoId: Int?,
    private val manitoNm: String?,
    private val confirm: () -> Unit
) : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val view = it.layoutInflater.inflate(R.layout.dialog_notice_manito, null).apply {
                setPopupEndFruit(popup_end_iv, manitoFruttoId)
                manitto_name_tv.text = manitoNm
                confirm_btn.setOnClickListener {
                    confirm()
                    dismiss()
                }
            }
            AlertDialog.Builder(it).apply {
                setView(view)
            }.create().apply {
                window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            }
        } ?: throw IllegalAccessException("Activity connot be null")
    }
}