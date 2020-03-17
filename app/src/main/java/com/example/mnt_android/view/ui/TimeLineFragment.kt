package com.example.mnt_android.view.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mnt_android.R
import com.example.mnt_android.base.BaseFragment
import com.example.mnt_android.databinding.FragmentTimeLineBinding
import com.example.mnt_android.util.TAG_IS_MANAGER
import com.example.mnt_android.view.adapter.ContentListAdapter
import com.example.mnt_android.viewmodel.TimeLineViewModel
import kotlinx.android.synthetic.main.fragment_time_line.*
import org.koin.android.viewmodel.ext.android.viewModel

class TimeLineFragment(private val isManager: Boolean = false) : BaseFragment() {
    companion object {
        private const val TAG = "TimeLine Filter Bottom Sheet"
    }

    private val viewModel by viewModel<TimeLineViewModel>()
    private lateinit var binding: FragmentTimeLineBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = bind(inflater, container, R.layout.fragment_time_line)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun initializeUI() {
        content_rv.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = ContentListAdapter(isManager)
        }

        setEventListener()
    }

    private fun setEventListener() {
        manito_list_btn.setOnClickListener {
            val intent = Intent(context, ManitoActivity::class.java)
            intent.putExtra(TAG_IS_MANAGER, isManager)
            startActivity(intent)
        }
        filter_btn.setOnClickListener {
            val supportFragmentManager = (context as FragmentActivity).supportFragmentManager
            val missionFilterBottomSheet = MissionFilterBottomSheet()
            missionFilterBottomSheet.show(supportFragmentManager, TAG)
        }
    }
}
