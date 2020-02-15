package com.example.mnt_android.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.mnt_android.R
import com.example.mnt_android.databinding.FragmentCreateroom2Binding
import com.example.mnt_android.viewmodel.CreateRoomViewModel
import kotlinx.android.synthetic.main.fragment_createroom2.*

class CreateRoomFragment2 : Fragment()
{
    lateinit var createRoomViewModel: CreateRoomViewModel
    lateinit var binding: FragmentCreateroom2Binding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_createroom2,container,false)

        createRoomViewModel = (activity as CreateRoomActivity).createRoomViewModel


        binding.createRoomViewModel=createRoomViewModel
        binding.createRoomActivity=(activity as CreateRoomActivity)


        return binding.root
        // return inflater.inflate(R.layout.activity_createroom2,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var maxpeoplespinadapter = ArrayAdapter.createFromResource(context,R.array.max_people,R.layout.spinner_item)
        maxpeoplespinadapter.setDropDownViewResource(R.layout.spinner_dropdown_item)
        spinner_maxpeople.adapter=maxpeoplespinadapter

        spinner_maxpeople.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                createRoomViewModel.maxPeople= (position+1).toString()
            }
        }

    }



}