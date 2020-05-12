package com.nexters.frutto.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<viewDataBinding : ViewDataBinding, baseViewModel : BaseViewModel> : AppCompatActivity(){
    lateinit var dataBinding: viewDataBinding
    abstract val viewModel: baseViewModel
    abstract val layoutId: Int

    abstract fun initSetting()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, layoutId)
        initSetting()
    }
}