package com.example.mnt_android.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import com.example.mnt_android.R
import com.example.mnt_android.base.BaseFragment
import com.example.mnt_android.databinding.FragmentDashBoardApplicantBinding
import com.example.mnt_android.view.dialog.CustomAlertDialog
import com.example.mnt_android.view.dialog.NoticeDialog
import com.example.mnt_android.viewmodel.DashBoardViewModel
import kotlinx.android.synthetic.main.fragment_dash_board_applicant.*
import org.jetbrains.anko.sdk21.listeners.onClick
import org.koin.android.viewmodel.ext.android.viewModel

class DashBoardApplicantFragment : BaseFragment() {
    companion object {
        private const val TAG = "Dashboard Applicant Dialog"
    }

    private val viewModel by viewModel<DashBoardViewModel>()
    private lateinit var binding: FragmentDashBoardApplicantBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = bind(inflater, container, R.layout.fragment_dash_board_applicant)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel.apply {
            loadDashBoard()
        }
        return binding.root
    }

    override fun initializeUI() {
        setEventListener()
    }

    private fun setEventListener() {
        val supportFragmentManager = (context as FragmentActivity).supportFragmentManager
        notification_switch.onClick {
            viewModel.setOnNotification(!notification_switch.isChecked)
        }
        dev_info_layout.onClick {
            NoticeDialog("개발자정보", "고민중").show(supportFragmentManager, TAG)
        }
        exit_room_layout.onClick {
            CustomAlertDialog(
                "당신의 호의를 기다리는 마니또를 생각하세요.",
                "확인"
            ).show(
                supportFragmentManager,
                TAG
            )
        }
    }
}
