package com.example.mnt_android.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mnt_android.service.model.User
import com.example.mnt_android.view.adapter.ApplicantListAdapter

@BindingAdapter("adapterApplicantList")
fun bindAdapterApplicantList(view: RecyclerView, applicantList: ArrayList<User>?) {
    applicantList?.let {
        val adapter = view.adapter as ApplicantListAdapter
        adapter.setList(it)
    }
}