package com.example.mnt_android.view.ui

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context.CLIPBOARD_SERVICE
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.mnt_android.R
import com.example.mnt_android.databinding.FragmentCreateroom3Binding
import com.example.mnt_android.view.dialog.ConfirmDialog
import com.example.mnt_android.viewmodel.CreateRoomViewModel
import kotlinx.android.synthetic.main.fragment_createroom3.*
import org.jetbrains.anko.sdk21.listeners.onClick

class CreateRoomFragment3 : Fragment()
{
    companion object {
        private const val TAG = "Delete Room Dialog"
    }

    lateinit var createRoomViewModel: CreateRoomViewModel
    lateinit var binding: FragmentCreateroom3Binding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_createroom3,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.let {
            createRoomViewModel = (activity as CreateRoomActivity).createRoomViewModel


            binding.createRoomViewModel=createRoomViewModel
            binding.createRoomActivity = (activity as CreateRoomActivity)
            binding.lifecycleOwner=this
        }

        setEventListener()
    }

    private fun setEventListener() {
        close_btn.onClick {
            val supportFragmentManager = (context as FragmentActivity).supportFragmentManager
            ConfirmDialog(
                "방을 삭제하시겠습니까?",
                "취소",
                "나가기"
            ) {
                createRoomViewModel.deleteRoom()
                val i = Intent(context, MainActivity::class.java)
                context?.startActivity(i)
            }.show(
                supportFragmentManager,
                TAG
            )
        }
        iv_copy.onClick {
            val roomNum = tv_roomnum_createroom3.text
            val clipboardManager = it?.context?.getSystemService(CLIPBOARD_SERVICE)
            val clipData = ClipData.newPlainText("RoomNum", roomNum)
            (clipboardManager as ClipboardManager).primaryClip = clipData
            Toast.makeText(context, "초대코드(${roomNum})가 복사되었습니다.", Toast.LENGTH_SHORT).show()
        }
    }


}