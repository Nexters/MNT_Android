package com.example.mnt_android.view.ui

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import com.example.mnt_android.R
import com.example.mnt_android.service.model.Applicant
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_mission_filter.*
import org.jetbrains.anko.textColor
import org.jetbrains.anko.windowManager

class MissionFilterBottomSheet : BottomSheetDialogFragment() {
    var missionList: Array<String> = arrayOf()
    var participantList: ArrayList<Applicant> = arrayListOf()

    var missionToMeOnClickListener: () -> Unit = {}
    var missionFromMeOnClickListener: () -> Unit = {}
    var missionListOnClickListener: () -> Unit = {}
    var participantListOnClickListener: () -> Unit = {}

    @SuppressLint("RestrictedApi")
    override fun setupDialog(dialog: Dialog, style: Int) {
        super.setupDialog(dialog, style)
        val contentView = View.inflate(context, R.layout.fragment_mission_filter, null)
        dialog.setContentView(contentView)
        (contentView.parent as View).setBackgroundColor(Color.TRANSPARENT)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setEventListener()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_mission_filter, container, false)
    }

    private fun setEventListener() {
        mission_to_me_btn.setOnClickListener { missionToMeOnClickListener }
        mission_from_me_btn.setOnClickListener { missionFromMeOnClickListener }
        view_by_mission_btn.setOnClickListener {
            view_by_mission_layout.run {
                if (visibility == View.VISIBLE) {
                    visibility = View.GONE
                } else {
                    visibility = View.VISIBLE
                    missionList.forEach {
                        addView(getTv(it))
                    }
                }
            }
        }
        view_by_participant_btn.setOnClickListener {
            view_by_participant_layout.run {
                if (visibility == View.VISIBLE) {
                    visibility = View.GONE
                } else {
                    visibility = View.VISIBLE
                    missionList.forEach {
                        addView(getTv(it))
                    }
                }
            }
        }
        close_btn.setOnClickListener { dismiss() }
    }

    private fun getTv(mission: String? = "") = TextView(context).apply {
        val ms = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 17f, context.resources.displayMetrics).toInt()
        text = mission
        textColor = ResourcesCompat.getColor(resources, R.color.colorLightBlueBlack, null)
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 15f)
        ellipsize = TextUtils.TruncateAt.END
        maxLines = 1
        layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        ).apply {
            setPadding(0, ms, 0, ms)
        }
    }
}