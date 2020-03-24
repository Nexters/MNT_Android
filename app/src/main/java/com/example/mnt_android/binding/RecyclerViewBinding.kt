package com.example.mnt_android.binding

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mnt_android.service.model.Applicant
import com.example.mnt_android.service.model.UserMissionResponse
import com.example.mnt_android.util.FALSE_INT
import com.example.mnt_android.view.adapter.*

@BindingAdapter("adapterApplicantList", "isManager")
fun bindAdapterApplicantList(
    view: RecyclerView,
    applicantList: ArrayList<Applicant>?,
    _isManager: Int = FALSE_INT
) {
    applicantList?.let { list ->
        (view.adapter as ApplicantListAdapter).run {
            isManager = _isManager
            setList(list)
        }
    }
}

@BindingAdapter("adapterManitoList", "isManager")
fun bindAdapterManitoList(
    view: RecyclerView,
    memberList: ArrayList<Applicant>?,
    isManager: Boolean = false
) {
    memberList?.let { list ->
        (view.adapter as ManitoListAdapter).run {
            this.isManager = isManager
            setList(list)
        }
    }
}

@BindingAdapter("adapterMissionManagerList")
fun bindAdapterMissionManagerList(
    view: RecyclerView,
    missionList: ArrayList<Pair<String,String>>?
) {
    missionList?.let { list ->
        (view.adapter as MissionManagerListAdapter).run {
            setList(list)
        }
    }
}

@BindingAdapter("adapterNotDoneMissionTypeList")
fun bindAdapterNotDoneMissionTypeList(
    view: RecyclerView,
    missionTypeList: ArrayList<UserMissionResponse>?
) {
    missionTypeList?.let { list ->
        (view.adapter as NotDoneMissionListAdapter).run {
            setList(list)
        }
    }
}

@BindingAdapter("adapterDoneMissionTypeList")
fun bindAdapterDoneMissionTypeList(
    view: RecyclerView,
    missionTypeList: ArrayList<UserMissionResponse>?
) {
    missionTypeList?.let { list ->
        (view.adapter as DoneMissionListAdapter).run {
            setList(list)
        }
    }
}

@BindingAdapter("adapterMissionList", "isManager")
fun bindAdapterMissionList(
    view: RecyclerView,
    missionList: ArrayList<UserMissionResponse>?,
    _isManager: Boolean = false
) {
    missionList?.let { list ->
        (view.adapter as ContentListAdapter).run {
            isManager = _isManager
            setList(list)
        }
    }
}

@BindingAdapter("android:src")
fun setIvSrc(view: ImageView, imgSrc: String?) {
    imgSrc?.let {
        Glide.with(view.context)
            .load(it)
            .into(view)
    } ?: { view.visibility = View.GONE }()
}