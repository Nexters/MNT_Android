package com.example.mnt_android.view.ui

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.mnt_android.R
import com.example.mnt_android.base.BaseFragment
import com.example.mnt_android.databinding.FragmentTimeLineBinding

class TimeLineFragment : BaseFragment<FragmentTimeLineBinding>() {
    override val layoutId: Int
        get() = R.layout.fragment_time_line
}
