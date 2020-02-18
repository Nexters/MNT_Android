package com.example.mnt_android.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import com.example.mnt_android.R
import com.example.mnt_android.base.BaseFragment
import com.example.mnt_android.databinding.FragmentDashBoardManagerBinding
import com.example.mnt_android.view.dialog.ConfirmDialog
import com.example.mnt_android.view.dialog.NoticeDialog
import kotlinx.android.synthetic.main.fragment_dash_board_manager.*

class DashBoardManagerFragment : BaseFragment() {
    companion object {
        private const val TAG = "DashBoard Manager Dialog"
    }

    private lateinit var binding: FragmentDashBoardManagerBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = bind(inflater, container, R.layout.fragment_dash_board_manager)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun initializeUI() {
        setEventListener()
    }

    private fun setEventListener() {
        val supportFragmentManager = (context as FragmentActivity).supportFragmentManager
        dev_info_layout.setOnClickListener {
            NoticeDialog("개발자정보", "고민중").show(supportFragmentManager, TAG)
        }

        exit_room_layout.setOnClickListener {
            ConfirmDialog(
                "푸르또 방을 나가시겠습니까?\n한번 나가면 다시 들어올 수 없습니다.",
                "취소",
                "나가기"
            ) {
            }.show(
                supportFragmentManager,
                TAG
            )
        }
    }
}
