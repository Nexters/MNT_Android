package com.nexters.frutto.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder(val view: View) : RecyclerView.ViewHolder(view){
    abstract fun onBind(position: Int, data: Any)
}