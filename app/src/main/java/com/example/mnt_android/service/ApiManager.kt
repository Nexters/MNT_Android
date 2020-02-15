package com.example.mnt_android.service

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ApiManager
{
    val gson =
        GsonBuilder()
            .setDateFormat("yyyy-MM-dd")
            .setLenient()
            .create()

    private val okHttpClientBuilder =
        OkHttpClient.Builder()
            .addInterceptor(provideLoggingInterceptor())//App Intercept
            .build()

    private val dbAdapter by lazy {
        Retrofit.Builder()
            .baseUrl("http://ec2-15-164-216-156.ap-northeast-2.compute.amazonaws.com:8888/")//기본적으로 통신할 API주소
            .client(okHttpClientBuilder)//통신을 할 주체?(Ok HTTP)
            .addConverterFactory(GsonConverterFactory.create(gson))//받은 응답을 Observable로 반환가능
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//Json 형식으로 Data를 보내고 이를 파싱가능
            .build()
    }

    val dbApi: DBApi by lazy {
        dbAdapter.create(
            DBApi::class.java)
    }

    private fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        return interceptor
    }
}