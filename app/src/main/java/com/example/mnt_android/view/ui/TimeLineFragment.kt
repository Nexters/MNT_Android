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
import com.example.mnt_android.bus.MISSION_LIST_ALL
import com.example.mnt_android.bus.filteringEventBus
import com.example.mnt_android.databinding.FragmentTimeLineBinding
import com.example.mnt_android.view.adapter.ContentListAdapter
import com.example.mnt_android.viewmodel.TimeLineViewModel
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_time_line.*
import org.koin.android.viewmodel.ext.android.viewModel

class TimeLineFragment : BaseFragment() {
    companion object {
        private const val TAG = "TimeLine Filter Bottom Sheet"
    }

    private val viewModel by viewModel<TimeLineViewModel>()
    private lateinit var binding: FragmentTimeLineBinding

    private val disposable = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = bind(inflater, container, R.layout.fragment_time_line)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel.apply {
            setFilteredContentList(arrayOf(MISSION_LIST_ALL))
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        disposable.clear()
    }

    override fun initializeUI() {
        content_rv.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = ContentListAdapter()
        }

        setEventListener()
        setEventBus()
    }

    private fun setEventListener() {
        swipe_layout.setOnRefreshListener {
            viewModel.setFilteredContentList()
            swipe_layout.isRefreshing = false
        }
        manito_list_btn.setOnClickListener {
            val intent = Intent(context, ManitoActivity::class.java)
            startActivity(intent)
        }
        filter_btn.setOnClickListener {
            val supportFragmentManager = (context as FragmentActivity).supportFragmentManager
            val missionFilterBottomSheet =
                MissionFilterBottomSheet(viewModel.getUserId(), viewModel.getIsManager())
            missionFilterBottomSheet.show(supportFragmentManager, TAG)

            viewModel.run {
                setMissionList {
                    missionFilterBottomSheet.missionList = it
                }
                setUserList {
                    missionFilterBottomSheet.participantList = it
                }
            }
        }
    }

    private fun setEventBus() {
        disposable.add(
            filteringEventBus.subscribe { filterType ->
                viewModel.setFilteredContentList(filterType)
            }
        )
    }
}
