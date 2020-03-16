package com.example.mnt_android.view.ui

import android.app.Dialog
import android.graphics.Color
import android.view.View
import com.example.mnt_android.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class MissionFilterBottomSheet : BottomSheetDialogFragment() {
    override fun setupDialog(dialog: Dialog, style: Int) {
        super.setupDialog(dialog, style)
        val contentView = View.inflate(context, R.layout.fragment_mission_filter, null)
        dialog.setContentView(contentView)
        (contentView.parent as View).setBackgroundColor(Color.TRANSPARENT)
    }
}