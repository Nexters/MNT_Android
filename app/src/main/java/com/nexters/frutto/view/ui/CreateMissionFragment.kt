package com.nexters.frutto.view.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.nexters.frutto.R
import com.nexters.frutto.databinding.FragmentCreatemission1Binding
import com.nexters.frutto.view.dialog.ConfirmDialog
import com.nexters.frutto.view.dialog.CustomAlertEmoticonDialog
import com.nexters.frutto.viewmodel.CreateMissionViewModel
import kotlinx.android.synthetic.main.fragment_createmission1.*
import kotlinx.android.synthetic.main.fragment_createmission1.text_length_tv
import org.jetbrains.anko.sdk21.listeners.onClick

class CreateMissionFragment : Fragment() {
    lateinit var createMissionViewModel: CreateMissionViewModel
    lateinit var binding: FragmentCreatemission1Binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_createmission1, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.let {
            createMissionViewModel = ViewModelProviders.of(this)[CreateMissionViewModel::class.java]
            binding.createMissionViewModel = createMissionViewModel
            binding.createMissionActivity = activity as CreateMissionActivity

            binding.lifecycleOwner = this
        }

        createMissionViewModel.isCreated.observe(context as LifecycleOwner, Observer {
            if (it == false) {
                (activity as CreateMissionActivity).finish()
            }
        })

        setSpinner()
        setEventListener()
    }

    private fun setSpinner() {
        val items = resources.getStringArray(R.array.arr_create_mission)
        val adapter =
            object : ArrayAdapter<String>(requireContext(), R.layout.spinner_mission) {
                override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                    val v = super.getView(position, convertView, parent)
                    if (position == count) {
                        (v as TextView).apply {
                            text = ""
                            hint = getItem(count)
                        }
                    }
                    return v
                }

                override fun getCount() = super.getCount() - 1
            }
        adapter.addAll(items.toMutableList())
        adapter.add("미션 선택하기")
        spin_mission_name_create_mission.adapter = adapter
        spin_mission_name_create_mission.setSelection(adapter.count)


        spin_mission_name_create_mission.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) = Unit
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    bu_create_mission_create_mission.isEnabled = position < adapter.count
                    if (position < adapter.count) createMissionViewModel.setMission(position)
                    else createMissionViewModel.des.value = ""
                }
            }
    }

    private fun setEventListener() {
        bu_create_mission_create_mission.setOnClickListener {
            val supportFragmentManager = (context as FragmentActivity).supportFragmentManager
            ConfirmDialog("참가자들에게 미션이 부여되며\n알림이 울립니다.", "취소", "보내기")
            {
                createMissionViewModel.makeMission()
                CustomAlertEmoticonDialog("\uD83C\uDF89", "참가자들에게 미션을 전달했습니다.", "확인") {
                    createMissionViewModel.isCreated.value = false
                }
                    .show(
                        supportFragmentManager,
                        "CreateMission"
                    )
            }.show(supportFragmentManager, "CreateMission")
        }
        back_btn.onClick { activity?.finish() }
        et_mission_description_create_mission.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) = Unit
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val length = p0?.length ?: 0
                text_length_tv.text = length.toString()
            }
        })
    }
}