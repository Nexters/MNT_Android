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

object KakaoMessageService {
    private const val TAG = "KAKAO_API"
    private const val BASE_URL = "https://play.google.com/store/apps/details?id=com.nexters.frutto"
    private const val DEFAULT_IMG_URL = "https://frutto-s3-storage.s3.ap-northeast-2.amazonaws.com/%E1%84%8B%E1%85%A1%E1%84%8B%E1%85%B5%E1%84%8F%E1%85%A9%E1%86%AB.png"
    private const val BTN_TITLE = "앱에서 보기"

    const val ROOM_NUM = "roomnum"
    const val MISSION_CONTENT = "mission_content"

    private val responseCallBack = object : ResponseCallback<KakaoLinkResponse>() {
        override fun onSuccess(result: KakaoLinkResponse?) {
            Log.w(TAG, "warning messages: " + result?.warningMsg)
            Log.w(TAG, "argument messages: " + result?.argumentMsg)
        }

        override fun onFailure(errorResult: ErrorResult?) {
            Log.e(TAG, "Fail : ${errorResult.toString()}")
        }
    }

    fun sendRoomNum(context: Context, roomNm: String, roomNum: Long) {
        val link = LinkObject.newBuilder()
            .setAndroidExecutionParams("$ROOM_NUM=$roomNum")
            .setIosExecutionParams("$ROOM_NUM=$roomNum")
            .setWebUrl(BASE_URL)
            .setMobileWebUrl(BASE_URL)
            .build()

        val params = FeedTemplate
            .newBuilder(
                ContentObject.newBuilder(
                    "'${roomNm}'방을 생성하였습니다.\n초대코드 : $roomNum",
                    DEFAULT_IMG_URL,
                    link
                ).build()
            )
            .addButton(
                ButtonObject(
                    BTN_TITLE,
                    link
                )
            )
            .build()

        KakaoLinkService.getInstance().sendDefault(context, params, responseCallBack)
    }

    fun shareMission(context: Context, mission: UserMissionResponse) {
        try {
            val link = LinkObject.newBuilder()
                .setAndroidExecutionParams("$MISSION_CONTENT=${Gson().toJson(mission)}")
                .setIosExecutionParams("$MISSION_CONTENT=${Gson().toJson(mission)}")
                .setWebUrl(BASE_URL)
                .setMobileWebUrl(BASE_URL)
                .build()

            val params = FeedTemplate
                .newBuilder(
                    ContentObject.newBuilder(
                        mission.missionName,
                        mission.userMission.missionImg ?: DEFAULT_IMG_URL,
                        link
                    )
                        .setDescrption(mission.userMission.content ?: "")
                        .build()
                )
                .addButton(
                    ButtonObject(
                        BTN_TITLE,
                        link
                    )
                )
                .build()

            KakaoLinkService.getInstance().sendDefault(context, params, responseCallBack)
        } catch (e: KakaoParameterException) {
            e.printStackTrace()
        }
    }
}