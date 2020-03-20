package com.example.mnt_android.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mnt_android.service.model.Applicant
import com.example.mnt_android.service.model.UserMissionResponse
import com.example.mnt_android.util.FALSE_INT
import com.example.mnt_android.view.adapter.ApplicantListAdapter
import com.example.mnt_android.view.adapter.ContentListAdapter
import com.example.mnt_android.view.adapter.ManitoListAdapter
import com.example.mnt_android.view.adapter.MissionManagerListAdapter

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