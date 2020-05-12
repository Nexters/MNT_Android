package com.nexters.frutto.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.nexters.frutto.R
import com.nexters.frutto.databinding.FragmentLogin2Binding
import com.nexters.frutto.viewmodel.LoginViewModel
import java.io.BufferedReader
import java.io.InputStreamReader


class LoginFragment2 : Fragment()
{
    lateinit var loginViewModel: LoginViewModel
    lateinit var binding: FragmentLogin2Binding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login2,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.let {
            loginViewModel=(activity as LoginActivity).loginViewModel

            binding.loginViewModel = (activity as LoginActivity).loginViewModel
            binding.loginActivity=(activity as LoginActivity)
            binding.lifecycleOwner=this
            binding.tvNameLogin2.text = readText()
        }
    }

    private fun readText(): String {
        val reader =
            BufferedReader(InputStreamReader(requireContext().assets.open("TermsOfService")))
        val sb = StringBuilder()
        var line: String? = reader.readLine()
        while (line != null) {
            if (line == "") sb.append("\n\n")
            else sb.append(line)
            line = reader.readLine()
        }
        reader.close()
        return sb.toString()
    }
}