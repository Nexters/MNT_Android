package com.nexters.frutto.view.ui

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.nexters.frutto.R
import com.nexters.frutto.databinding.ActivityLoginBinding
import com.nexters.frutto.service.model.KakaoUser
import com.nexters.frutto.service.repository.SessionCallback
import com.nexters.frutto.viewmodel.BackPressViewModel
import com.nexters.frutto.viewmodel.LoginViewModel
import com.kakao.auth.AuthType
import com.kakao.auth.Session

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

        sf = getSharedPreferences("login",0)
        editor = sf?.edit()

       loginViewModel = ViewModelProviders.of(this)[LoginViewModel(application)::class.java]

       loginViewModel.kuser.nickname.observe(this,object : Observer<String?> {
            override fun onChanged(t: String?) {
                Log.d(TAG,t)
                if(t!= sf?.getString("kakao_nickname","null"))
                {
                   setFrag(1)

                }
            }
        })

        loginViewModel.isLogined.observe(this, Observer {
            if(it==true)
            {
                editor?.putString("kakao_nickname",loginViewModel.kuser.nickname.value)
                editor?.putString("kakao_id",loginViewModel.kuser.id)
                editor?.putString("kakao_token",loginViewModel.kuser.token)
                editor?.commit()
            }
            else
            {
                Toast.makeText(this@LoginActivity,"로그인에 실패하였습니다.",Toast.LENGTH_SHORT).show()
            }
        })

        setFrag(0)




    }

    fun login()
    {
        //눌러서 로그인
       val callback = SessionCallback(application)
        Session.getCurrentSession().addCallback(callback)
        Session.getCurrentSession().checkAndImplicitOpen()
        Session.getCurrentSession().open(AuthType.KAKAO_ACCOUNT,this@LoginActivity)
        loginViewModel.kuser = callback.user
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
                loginViewModel.top_login2=loginViewModel.kuser.nickname.value+"님, 반가워요"
                fragmentTransaction.replace(R.id.frag_login,loginFragment3)
                loginViewModel.fragmentNum=2
                fragmentTransaction.commit()
            }


        }
    }


    fun createAccount()
    {
        loginViewModel.createAccount()
        editor?.putString("kakao_token",loginViewModel.kuser.token)
        editor?.commit()
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
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
        var kuser : KakaoUser?=null
    }

}





