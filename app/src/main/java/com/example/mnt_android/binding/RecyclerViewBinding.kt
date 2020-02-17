package com.example.mnt_android.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mnt_android.service.model.Applicant
import com.example.mnt_android.view.adapter.ApplicantListAdapter

@BindingAdapter("adapterApplicantList", "isManager")
fun bindAdapterApplicantList(view: RecyclerView, applicantList: ArrayList<Applicant>?, isManager: Int = 0) {
    applicantList?.let {
        val adapter = view.adapter as ApplicantListAdapter
        adapter.isManager = isManager
        adapter.setList(it)
    }
}
