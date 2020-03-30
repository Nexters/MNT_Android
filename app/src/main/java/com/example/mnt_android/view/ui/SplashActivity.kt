package com.example.mnt_android.view.ui

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.mnt_android.R
import com.example.mnt_android.extension.isTrue
import com.example.mnt_android.util.TAG_IS_MANAGER
import com.example.mnt_android.viewmodel.JoinRoomViewModel
import com.example.mnt_android.viewmodel.SplashViewModel

class SplashActivity : AppCompatActivity()
{
    lateinit var splashViewModel: SplashViewModel
    lateinit var joinRoomViewModel : JoinRoomViewModel

    var editor : SharedPreferences.Editor?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash)
        splashViewModel = ViewModelProviders.of(this)[SplashViewModel::class.java]
        joinRoomViewModel = ViewModelProviders.of(this)[JoinRoomViewModel::class.java]

        joinRoomViewModel.checkRoom()

        var sf =getSharedPreferences("login",0)
        editor = sf.edit()
        Handler().postDelayed({
            if(sf.getString("kakao_token","null")!="null")
            {
                //이미 로그인을 했었다
                joinRoomViewModel.isJoined.observe(this, Observer {
                    val intent = intent
                    val str = intent.data
                    if(it==true)
                    {
                        editor!!.putLong("roomId", joinRoomViewModel.checkRoom.value!!.room.id)
                        editor!!.commit()

                        if(str!=null)
                        {

                        }
                        //참가한 방이 존재
                        if (joinRoomViewModel.isStarted.value == false) {
                            //방장이 방을 시작하지 않음
                            //인원이 부족할 때 예외처리를 위해 response를 바꿔야함
                            if (joinRoomViewModel.isManager.value == true)
                            {


                                editor!!.putBoolean("isManager", true)
                                editor!!.commit()

                                val intent = Intent(this, CreateRoomActivity::class.java)
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
                                intent.putExtra(TAG_IS_MANAGER,true)
                                intent.putExtra("fragNum", 2)
                                intent.putExtra("checkRoom", joinRoomViewModel.checkRoom.value)
                                intent.putExtra("roomName",joinRoomViewModel.checkRoom.value!!.room.name)
                                startActivity(intent)

                            }
                            else
                            {
                                val intent = Intent(this, JoinRoomActivity::class.java)
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
                                intent.putExtra("fragNum", 1)
                                intent.putExtra("checkRoom", joinRoomViewModel.checkRoom.value)
                                startActivity(intent)
                            }

                        }
                        else
                        {
                            joinRoomViewModel.pr.setIsManager(joinRoomViewModel.isManager.value)
                            //방장이 방을 시작함
                            if (joinRoomViewModel.isDone.isTrue) {
                                val cls = when(joinRoomViewModel.pr.getCheckNaeto()) {
                                    true -> GameActivity::class.java
                                    false -> FinishActivity::class.java
                                }
                                val i = Intent(baseContext, cls)
                                startActivity(i)
                            }
                            else if (joinRoomViewModel.isManager.value == true)
                            {
                                //내가 방장이고 방이 이미 시작됨
                                val intent = Intent(this, GameActivity::class.java)
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
                                intent.putExtra(TAG_IS_MANAGER,true)
                                intent.putExtra("roomName",joinRoomViewModel.checkRoom.value!!.room.name)
                                startActivity(intent)

                            }
                            else if (joinRoomViewModel.pr.getCheckNito()) {
                                //내 마니또를 확인했음
                                val intent = Intent(this, GameActivity::class.java)
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
                                intent.putExtra("checkRoom", joinRoomViewModel.checkRoom.value)
                                startActivity(intent)
                            } else {
                                //내 마니또를 확인하지 못함

                                joinRoomViewModel.pr.setFruttoId(joinRoomViewModel.fruttoId)
                                joinRoomViewModel.pr.setManitoNm(joinRoomViewModel.nittoName)
                                joinRoomViewModel.pr.setManitoFruttoId(joinRoomViewModel.manitoFruttoId)
                                editor!!.commit()

//                                val intent = Intent(this, JoinRoomActivity::class.java)
//                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
//                                intent.putExtra("fragNum", 2)
//                                intent.putExtra("checkRoom", joinRoomViewModel.checkRoom.value)
//                                startActivity(intent)

                                val i = Intent(this, ShowManittoActivity::class.java)
                                i.putExtra(ShowManittoActivity.TAG_END_DAY, joinRoomViewModel.endDay)
                                startActivity(i)
                            }
                        }

                    }
                    else
                    {
                        Toast.makeText(this,"참가한 방이 존재하지 않습니다.", Toast.LENGTH_SHORT).show()
                        //참가한 방이 없다

                        if(str!=null)
                        {
                            val intent = Intent(this, JoinRoomActivity::class.java)
                            intent.putExtra("fragNum", 0)
                            intent.putExtra("roomNum", intent.data.getQueryParameter("roomnum"))
                            startActivity(intent)
                        }

                        val intent = Intent(this, MainActivity::class.java)
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
                        startActivity(intent)

                    }
                })


            }
            else
            {
                //한번도 로그인을 하지 않았다
                var intent = Intent(this,LoginActivity::class.java)
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
                startActivity(intent)
                finish()
            }


        },2000)

    }
}