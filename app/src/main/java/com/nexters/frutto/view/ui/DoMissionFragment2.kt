package com.nexters.frutto.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.nexters.frutto.R
import com.nexters.frutto.databinding.FragmentCreateroom1Binding
import com.nexters.frutto.databinding.FragmentDoMission1Binding
import com.nexters.frutto.databinding.FragmentDoMission2Binding
import com.nexters.frutto.view.dialog.CustomAlertDialog
import com.nexters.frutto.viewmodel.DoMissionViewModel
import kotlinx.android.synthetic.main.fragment_do_mission2.*
import org.jetbrains.anko.imageBitmap

class DoMissionFragment2 : Fragment()
{
    lateinit var doMissionViewModel: DoMissionViewModel
    lateinit var binding: FragmentDoMission2Binding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_do_mission2,container,false)



        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let{
            doMissionViewModel=(activity as DoMissionActivity).doMissionViewModel
            binding.doMissionActivity = activity as DoMissionActivity
            binding.doMissionViewModel = doMissionViewModel
            doMissionViewModel.bitmap?.let {
                iv_img_mission_do_mission2.imageBitmap = it
            } ?: { iv_img_mission_do_mission2.visibility = View.GONE }()


            binding.lifecycleOwner=this

            doMissionViewModel.isSended.observe(viewLifecycleOwner, Observer {
                if(it==true)
                {
                    CustomAlertDialog("니또에게 미션을 보냈습니다.","확인"){
                        activity?.finish()
                    }.show((activity as DoMissionActivity).supportFragmentManager,"SendMission")
                }
            })

        }

    }

}