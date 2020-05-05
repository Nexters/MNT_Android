package com.example.mnt_android.view.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.mnt_android.R
import com.example.mnt_android.viewmodel.JoinRoomViewModel
import com.example.mnt_android.databinding.FragmentJoinroom1Binding
import kotlinx.android.synthetic.main.fragment_joinroom1.*
import org.jetbrains.anko.sdk21.listeners.onClick

class JoinRoomFragment : Fragment()
{
    lateinit var joinRoomViewModel: JoinRoomViewModel
    lateinit var binding: FragmentJoinroom1Binding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_joinroom1,container,false)




        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.let {
            joinRoomViewModel = ViewModelProviders.of(this)[JoinRoomViewModel::class.java]

            joinRoomViewModel=(activity as JoinRoomActivity).joinRoomViewModel

            binding.joinRoomViewModel =joinRoomViewModel
            binding.joinRoomActivity=(activity as JoinRoomActivity)
            binding.lifecycleOwner=this
        }
        setEventListener()
    }

    private fun setEventListener(){
        delete_text_btn.onClick { et_roomnum_joinroom1.setText("")}
        et_roomnum_joinroom1.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(p0: Editable?) = Unit
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    val length = p0?.length ?: 0
                    text_length_tv.text = length.toString()
                    bu_join_joinroom1.isEnabled = length > 0
                }
            })
    }
}