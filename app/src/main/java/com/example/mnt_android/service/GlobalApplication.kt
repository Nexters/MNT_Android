package com.example.mnt_android.service


import android.app.Application
import com.example.mnt_android.di.networkModule
import com.kakao.auth.KakaoSDK
import org.koin.core.context.startKoin

class GlobalApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
        KakaoSDK.init(KakaoSDKAdapter())

        startKoin {
            modules(networkModule)
        }
    }



    fun getGlobalApplicationContext(): GlobalApplication {
        checkNotNull(instance) { "this application does not inherit com.kakao.GlobalApplication" }
        return instance!!
    }

    override fun onTerminate() {
        super.onTerminate()
        instance=null
    }
    companion object {
        var instance: GlobalApplication? = null
    }
}