package com.example.mnt_android.view.ui

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.mnt_android.R
import com.example.mnt_android.databinding.ActivityLoginBinding
import com.example.mnt_android.viewmodel.BackPressViewModel
import com.example.mnt_android.viewmodel.DoMissionViewModel
import com.example.mnt_android.viewmodel.LoginViewModel
import com.kakao.auth.Session
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity()
{
    val TAG = "LoginActivity.kt"

    lateinit var loginViewModel: LoginViewModel
    lateinit var loginFragment : LoginFragment
    lateinit var loginFragment2 : LoginFragment2
    lateinit var loginFragment3 : LoginFragment3
    lateinit var fragmentTransaction: FragmentTransaction
    lateinit var fragmentManager: FragmentManager
    lateinit var backPressViewModel : BackPressViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        fragmentManager=supportFragmentManager
        fragmentTransaction=fragmentManager.beginTransaction()
        loginFragment= LoginFragment()
        loginFragment2= LoginFragment2()
        loginFragment3= LoginFragment3()



       loginViewModel = ViewModelProviders.of(this)[LoginViewModel(application)::class.java]

        loginViewModel.user.nickname.observe(this,object : Observer<String?> {
            override fun onChanged(t: String?) {
                Log.d(TAG,t)
                if(t!= sf?.getString("kakao_id","null"))
                {
                   setFrag(1)

                }
            }
        })

        setFrag(0)




    }



    fun setFrag(n : Int)
    {
        fragmentTransaction = fragmentManager.beginTransaction()

        when(n)
        {
            0 ->
            {
                fragmentTransaction.replace(R.id.frag_login,loginFragment)
                loginViewModel.fragmentNum=0
                fragmentTransaction.commit()

            }
            1->
            {
                fragmentTransaction.replace(R.id.frag_login,loginFragment2)
                loginViewModel.fragmentNum=1
                fragmentTransaction.commit()
            }
            2->
            {
                fragmentTransaction.replace(R.id.frag_login,loginFragment3)
                loginViewModel.fragmentNum=2
                fragmentTransaction.commit()
            }


        }
    }


    fun setImage()
    {
        Toast.makeText(this,"Select Image", Toast.LENGTH_SHORT).show()
    }


    override fun onBackPressed() {

        when(loginViewModel.fragmentNum)
        {
            0->
            {
                finish()
            }
            1->
            {
                setFrag(0)
            }
            2->
            {
                setFrag(1)
            }
        }

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





