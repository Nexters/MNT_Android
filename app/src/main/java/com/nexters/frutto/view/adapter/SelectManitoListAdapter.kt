package com.nexters.frutto.view.adapter

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nexters.frutto.R
import com.nexters.frutto.base.BaseAdapter
import com.nexters.frutto.service.model.Applicant
import com.nexters.frutto.view.viewholder.SelectManitoListViewHolder

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