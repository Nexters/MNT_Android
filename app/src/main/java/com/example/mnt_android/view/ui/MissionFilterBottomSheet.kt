package com.example.mnt_android.view.ui

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.mnt_android.R
import com.example.mnt_android.service.model.Applicant
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_mission_filter.*


class MissionFilterBottomSheet : BottomSheetDialogFragment() {
    var missionList: Array<String> = arrayOf()
    var participantList: ArrayList<Applicant> = arrayListOf()

    var missionToMeOnClickListener: () -> Unit = {}
    var missionFromMeOnClickListener: () -> Unit = {}
    var missionListOnClickListener: (missionName: String) -> Unit = {}
    var participantListOnClickListener: (userId: String) -> Unit = {}

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
                        addView(getMissionItem(it))
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
                        addView(getUserItem("", 1, it))
                    }
                }
            }
        }
        close_btn.setOnClickListener { dismiss() }
    }

    private fun getMissionItem(mission: String = ""): LinearLayout {
        val inflater = LayoutInflater.from(context)
        return (inflater.inflate(R.layout.item_filter_list, null, false) as LinearLayout).apply {
            findViewById<ImageView>(R.id.iv).visibility = View.GONE
            findViewById<TextView>(R.id.tv).text = mission
            setOnClickListener { missionListOnClickListener(mission) }
        }
    }

    private fun getUserItem(userId: String, imgId: Int, title: String = ""): LinearLayout {
        val inflater = LayoutInflater.from(context)
        return (inflater.inflate(R.layout.item_filter_list, null, false) as LinearLayout).apply {
            val imgRes = resources.getIdentifier(
                "img_profile_face_${"%02d".format(imgId)}",
                "drawable",
                context?.packageName
            )
            findViewById<ImageView>(R.id.iv).setImageResource(imgRes)
            findViewById<TextView>(R.id.tv).text = title
            setOnClickListener { participantListOnClickListener(userId) }
        }
    }
}