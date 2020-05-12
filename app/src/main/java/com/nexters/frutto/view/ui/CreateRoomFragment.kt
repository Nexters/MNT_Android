package com.nexters.frutto.view.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.nexters.frutto.R
import com.nexters.frutto.databinding.FragmentCreateroom1Binding
import com.nexters.frutto.viewmodel.CreateRoomViewModel
import kotlinx.android.synthetic.main.fragment_createroom1.*
import org.jetbrains.anko.sdk21.listeners.onClick

class CreateRoomFragment : Fragment()
{
    lateinit var createRoomViewModel: CreateRoomViewModel
    lateinit var binding: FragmentCreateroom1Binding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_createroom1,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.let {
            createRoomViewModel=(activity as CreateRoomActivity).createRoomViewModel
            binding.createRoomViewModel = (activity as CreateRoomActivity).createRoomViewModel
            binding.createRoomActivity=(activity as CreateRoomActivity)
            binding.lifecycleOwner=this
        }

        setEventListener()

    }

    private fun setEventListener() {
        delete_text_btn.onClick { et_roomname_createroom1.setText("")}
        et_roomname_createroom1.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) = Unit
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val length = p0?.length ?: 0
                text_length_tv.text = length.toString()
                bu_next_createroom1.isEnabled = length > 0
            }
        })
    }




}