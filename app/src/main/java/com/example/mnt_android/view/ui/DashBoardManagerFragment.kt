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
import com.example.mnt_android.view.dialog.DevMentionDialog
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
            (activity as? GameActivity)?.selectMissionBtn()
        }

        notification_switch.onClick {
            viewModel.setOnNotification(notification_switch.isChecked)
        }

        dev_info_layout.onClick {
            DevMentionDialog("개발자정보",
                "\uD83D\uDE03You made my day\n" +
                        "\n\uD83D\uDCADTo infinity...and beyond\uD83E\uDD38\u200D♀️\n" +
                        "\n\uD83E\uDD70Viva la vida\n" +
                        "\n\uD83D\uDC31\n" +
                        "\n\uD83E\uDD7APlease... Don't forget me!!\n" +
                        "\n\uD83D\uDE4A\n" +
                        "\n\uD83D\uDE4B\u200D♂️Be my friend\n" +
                        "\n\uD83D\uDC7BJihye ZZANG","PM 대경\n\nGUI 소영\n\nUI 예희\n\nServer 지혜\n\n" +
                        "Android 현우\n\nAndroid 유진\n\nIOS 민섭\n\nIOS 지혜").show(supportFragmentManager,
                DashBoardManagerFragment.TAG
            )
        }

        exit_room_layout.onClick {
            if (viewModel.getCheckNaeto()) {
                ConfirmDialog(
                    "프루또 방을 나가시겠습니까?\n" +
                            "방을 나가면 다시 들어올 수 없습니다."
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
