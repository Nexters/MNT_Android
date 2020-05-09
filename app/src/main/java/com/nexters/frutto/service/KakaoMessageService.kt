package com.nexters.frutto.service

import android.content.Context
import android.util.Log
import com.nexters.frutto.service.model.UserMissionResponse
import com.google.gson.Gson
import com.kakao.kakaolink.v2.KakaoLinkResponse
import com.kakao.kakaolink.v2.KakaoLinkService
import com.kakao.message.template.*
import com.kakao.network.ErrorResult
import com.kakao.network.callback.ResponseCallback
import com.kakao.util.KakaoParameterException
import java.util.HashMap

object KakaoMessageService {
    private const val BASE_URL = "https://developers.kakao.com"
    private const val BTN_TITLE = "앱에서 보기"
    private const val RESPONSE_TAG = "kakaoLinkResponse"

    const val ROOM_NUM = "roomnum"
    const val MISSION_CONTENT = "mission_content"

    fun sendRoomNum(context: Context, roomNum: Long) {
        val params = TextTemplate
            .newBuilder(
                "마니또를 생성하였습니다",
                LinkObject.newBuilder().setAndroidExecutionParams("https://www.naver.com").build()
            )
            .addButton(
                ButtonObject(
                    "앱에서 보기",
                    LinkObject.newBuilder().setWebUrl("'https://www.naver.com'").setMobileWebUrl("'https://www.naver.com'")
                        .setAndroidExecutionParams("$ROOM_NUM=$roomNum").setIosExecutionParams("roomnum=$roomNum").build()
                )
            ).build()

        val serverCallbackArgs = HashMap<String, String>()
        val aaa = object : ResponseCallback<KakaoLinkResponse>() {
            override fun onSuccess(result: KakaoLinkResponse?) {}
            override fun onFailure(errorResult: ErrorResult?) {}
        }

        KakaoLinkService.getInstance().sendDefault(context, params, serverCallbackArgs, aaa)
    }

    fun shareMission(context: Context, mission: UserMissionResponse) {
        try {
            val params: TemplateParams = FeedTemplate
                .newBuilder(
                    ContentObject.newBuilder(
                        mission.missionName,
                        mission.userMission.missionImg ?: "",
                        LinkObject.newBuilder()
                            .setWebUrl(BASE_URL)
                            .setMobileWebUrl(BASE_URL)
                            .build()
                    )
                        .setDescrption(mission.userMission.content ?: "")
                        .build()
                )
                .addButton(
                    ButtonObject(
                        BTN_TITLE,
                        LinkObject.newBuilder()
                            .setAndroidExecutionParams("$MISSION_CONTENT=${Gson().toJson(mission)}")
                            .build()
                    )
                )
                .build()

            KakaoLinkService.getInstance()
                .sendDefault(context, params, object : ResponseCallback<KakaoLinkResponse>() {
                    override fun onSuccess(result: KakaoLinkResponse?) {}
                    override fun onFailure(errorResult: ErrorResult?) {
                        Log.d(RESPONSE_TAG, "Fail : ${errorResult.toString()}")
                    }
                })
        } catch (e: KakaoParameterException) {
            e.printStackTrace()
        }
    }
}