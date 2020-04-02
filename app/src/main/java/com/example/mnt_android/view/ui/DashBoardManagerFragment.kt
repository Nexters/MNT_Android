package com.example.mnt_android.view.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.example.mnt_android.R
import com.example.mnt_android.base.BaseFragment
import com.example.mnt_android.databinding.FragmentDashBoardManagerBinding
import com.example.mnt_android.view.dialog.ConfirmDialog
import com.example.mnt_android.view.dialog.NoticeDialog
import com.example.mnt_android.viewmodel.DashBoardViewModel
import kotlinx.android.synthetic.main.fragment_dash_board_manager.*
import org.jetbrains.anko.sdk21.listeners.onClick
import org.koin.android.viewmodel.ext.android.viewModel

class DashBoardManagerFragment : BaseFragment() {
    companion object {
        private const val TAG = "DashBoard Manager Dialog"
    }

    private val viewModel by viewModel<DashBoardViewModel>()
    private lateinit var binding: FragmentDashBoardManagerBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = bind(inflater, container, R.layout.fragment_dash_board_manager)
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
        created_mission_layout.onClick {
            val fragment = MissionManagerFragment()
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.game_layout, fragment)
            transaction.commit()
        }

        notification_switch.onClick {
            viewModel.setOnNotification(!notification_switch.isChecked)
        }

        dev_info_layout.onClick {
            NoticeDialog("개발자정보", "고민중").show(supportFragmentManager, TAG)
        }

        exit_room_layout.onClick {
            if (viewModel.getCheckNaeto()) {
                ConfirmDialog(
                    "프루또 방을 나가시겠습니까?\n" +
                            "한번 나가면 다시 들어올 수 없습니다."
                ) {
                    viewModel.exitRoom {
                        viewModel.clearManitoData()
                        val i = Intent(context, SplashActivity::class.java)
                        context?.startActivity(i)
                    }
                }.show(
                    supportFragmentManager,
                    TAG
                )
            } else {
                ConfirmDialog(
                    "프루또를 끝내시겠습니까?\n" +
                            "참여자들의 니또가 공개되며,\n" +
                            "더 이상 미션을 부여하고 수행할 수 없습니다.",
                    "취소",
                    "나가기"
                ) {
                    viewModel.endRoom()
                    viewModel.setCheckNaeto()
                    Toast.makeText(context, "프루또가 종료되었습니다.", Toast.LENGTH_SHORT).show()
                }.show(
                    supportFragmentManager,
                    TAG
                )
            }
        }
    }
}
