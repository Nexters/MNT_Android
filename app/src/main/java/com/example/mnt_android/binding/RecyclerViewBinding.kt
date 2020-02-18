package com.example.mnt_android.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mnt_android.service.model.Applicant
import com.example.mnt_android.util.FALSE
import com.example.mnt_android.view.adapter.ApplicantListAdapter
import com.example.mnt_android.view.adapter.ManitoListAdapter

@BindingAdapter("adapterApplicantList", "isManager")
fun bindAdapterApplicantList(view: RecyclerView, applicantList: ArrayList<Applicant>?, _isManager: Int = FALSE) {
    applicantList?.let { list ->
        (view.adapter as ApplicantListAdapter).run {
            isManager = _isManager
            setList(list)
        }
    }
}

@BindingAdapter("adapterManitoList", "isManager")
fun bindAdapterManitoList(view: RecyclerView, memberList: ArrayList<Applicant>?, isManager: Boolean = false) {
    memberList?.let {  list ->
        (view.adapter as ManitoListAdapter).run {
            this.isManager = isManager
            setList(list)
        }
    }
}