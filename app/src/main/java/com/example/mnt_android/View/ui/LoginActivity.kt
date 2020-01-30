package com.example.mnt_android.view.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.mnt_android.R
import com.example.mnt_android.databinding.ActivityLoginBinding
import com.example.mnt_android.databinding.ActivityMainBinding
import com.example.mnt_android.viewmodel.LoginViewModel
import com.kakao.auth.AuthType
import com.kakao.auth.ISessionCallback
import com.kakao.auth.Session
import com.kakao.network.ErrorResult
import com.kakao.usermgmt.StringSet.name
import com.kakao.usermgmt.UserManagement
import com.kakao.usermgmt.callback.MeV2ResponseCallback
import com.kakao.usermgmt.response.MeV2Response
import com.kakao.util.exception.KakaoException

class LoginActivity : AppCompatActivity()
{
    val TAG = "LoginActivity.kt"



    override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val binding = DataBindingUtil
        .setContentView<ActivityLoginBinding>(this, R.layout.activity_login)
    binding.lifecycleOwner = this // LiveData를 사용하기 위해서 없으면 Observe할때마다 refresh안딤

    val viewModel = ViewModelProviders.of(this)[LoginViewModel(application)::class.java]

    viewModel.user.nickname.observe(this,object : Observer<String?> {
        override fun onChanged(t: String?) {
            Log.d(TAG,t)
            if(t!= sf?.getString("kakao_id","null"))
            {
                val intent = Intent(this@LoginActivity, LoginActivity2::class.java)
                startActivity(intent)

            }
        }
    })


}

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(Session.getCurrentSession().handleActivityResult(requestCode,resultCode,data))
        {
            return
        }
        super.onActivityResult(requestCode, resultCode, data)



    }

    override fun onDestroy() {
        super.onDestroy()
    }




    companion object
    {
        var sf : SharedPreferences?=null
        var editor : SharedPreferences.Editor?=null
    }

}





