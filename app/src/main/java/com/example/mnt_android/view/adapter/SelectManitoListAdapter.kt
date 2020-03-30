package com.example.mnt_android.view.adapter

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mnt_android.R
import com.example.mnt_android.base.BaseAdapter
import com.example.mnt_android.service.model.Applicant
import com.example.mnt_android.view.viewholder.SelectManitoListViewHolder

class SelectManitoListAdapter : BaseAdapter<Applicant>() {
    override val layoutId: Int
        get() = R.layout.item_participant
    private val _item = MutableLiveData<View>()
    val item: LiveData<View>
        get() = _item
    private var itemIdx: String? = null

    override fun viewHolder(layout: Int, view: View) = SelectManitoListViewHolder(view) { idx, v ->
        _item.value?.isSelected = false
        _item.value = v
        v?.isSelected = true
        itemIdx = idx
    }

    fun getSelectedItem() = itemIdx
}