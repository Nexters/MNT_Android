package com.example.mnt_android.util

import android.content.Context
import com.example.mnt_android.vo.FruttoDataVO
import com.example.mnt_android.vo.FruttoListVO
import com.google.gson.Gson
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader


fun getFruttoDataList(context: Context): FruttoListVO {
    val gson = Gson()
    var source: InputStream? = null
    val assetManager = context.assets

    try {
        source = assetManager.open("FruttoData.json")
    } catch (e: IOException) {
        e.printStackTrace()
    }

    val reader = InputStreamReader(source)
    return gson.fromJson(reader, FruttoListVO::class.java)
}

fun getFruttoData(context: Context, id: Int): FruttoDataVO? {
    val fruttoDataList = getFruttoDataList(context)
    var fruttoData: FruttoDataVO? = null
    fruttoDataList.fruttoUserData.forEach checkData@{
        if (it.id == id+1) {
            fruttoData = it
            return@checkData
        }
    }
    return fruttoData
}