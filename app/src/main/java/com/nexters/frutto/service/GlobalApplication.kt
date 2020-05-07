package com.nexters.frutto.service


import android.app.Application
import com.nexters.frutto.di.moduleList
import com.kakao.auth.KakaoSDK
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class GlobalApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
        KakaoSDK.init(KakaoSDKAdapter())

        startKoin {
            androidContext(applicationContext)
            modules(moduleList)
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