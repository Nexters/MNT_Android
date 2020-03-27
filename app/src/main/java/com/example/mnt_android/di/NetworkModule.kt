package com.example.mnt_android.di

import com.example.mnt_android.service.DBApi
import com.example.mnt_android.service.repository.DBRepository
import com.example.mnt_android.viewmodel.*
import com.example.mnt_android.vo.FruttoListVO
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader

var networkModule = module {
    single {
        GsonBuilder()
            .setLenient()
            .create()
    }

    single {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
    }

    single {
        OkHttpClient.Builder()
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl("http://ec2-15-164-216-156.ap-northeast-2.compute.amazonaws.com:8888") //기본적으로 통신할 API주소
            .client(get()) //통신을 할 주체?(Ok HTTP)
            .addConverterFactory(GsonConverterFactory.create(get())) //Json 형식으로 Data를 보내고 이를 파싱가능
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) //받은 응답을 Observable로 반환가능
            .build()
    }

    single {
        get<Retrofit>().create(DBApi::class.java)
    }

    factory {
        DBRepository()
    }

    viewModel {
        ApplicantListViewModel(get())
    }

    viewModel {
        ManitoListViewModel(get())
    }

    viewModel { (userNm: String) ->
        TimeLineViewModel(userNm, get())
    }

    viewModel {
        MissionDetailViewModel()
    }

    viewModel {
        GameViewModel(get())
    }
}

var fileModule = module {
    single {
        var source: InputStream? = null
        val assetManager = androidContext().assets

        try {
            source = assetManager.open("FruttoData.json")
        } catch (e: IOException) {
            e.printStackTrace()
        }

        val reader = InputStreamReader(source)
        Gson().fromJson(reader, FruttoListVO::class.java)
    }
}

var moduleList = arrayListOf(networkModule, fileModule)