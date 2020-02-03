package com.example.mnt_android.service.model.dialog

import android.app.Dialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

// TODO: 전달받을 데이터 타입 수정 필요
class CustomDialog(private val message: String) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setMessage("$message CustomDialog")
                .setPositiveButton("Confirm") { dialog, id ->
                    Toast.makeText(context, "$message Confirm", Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                }
                .setNegativeButton("Cancel") { dialog, id ->
                    Toast.makeText(context, "$message Cancel", Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}