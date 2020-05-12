package com.nexters.frutto.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.nexters.frutto.R
import com.nexters.frutto.databinding.FragmentCreateroom2Binding
import com.nexters.frutto.viewmodel.CreateRoomViewModel
import kotlinx.android.synthetic.main.fragment_createroom2.*

class CreateRoomFragment2 : Fragment() {
    lateinit var createRoomViewModel: CreateRoomViewModel
    lateinit var binding: FragmentCreateroom2Binding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_createroom2, container, false)
        return binding.root
        // return inflater.inflate(R.layout.activity_createroom2,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.let {
            createRoomViewModel = (activity as CreateRoomActivity).createRoomViewModel
            binding.createRoomViewModel = (activity as CreateRoomActivity).createRoomViewModel
            binding.createRoomActivity = (activity as CreateRoomActivity)

            binding.lifecycleOwner = this
        }
        setSpinner()
    }

    private fun setSpinner() {
        spinner_maxpeople.run {
            adapter = ArrayAdapter.createFromResource(context,R.array.max_people,R.layout.spinner_max_people)
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) = Unit
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    createRoomViewModel.maxPeople =
                        spinner_maxpeople.getItemAtPosition(position).toString()
                }
            }
        }
    }
}