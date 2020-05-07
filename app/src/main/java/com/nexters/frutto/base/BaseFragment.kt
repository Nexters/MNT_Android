package com.nexters.frutto.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {
    abstract fun initializeUI()

    protected inline fun<reified T : ViewDataBinding> bind(
        inflater: LayoutInflater,
        container: ViewGroup?,
        layoutId: Int
    ): T =
        DataBindingUtil.inflate(inflater, layoutId, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeUI()
    }
}