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
    private const val DEFAULT_IMG_URL = "https://lh3.googleusercontent.com/proxy/40Ms12qINBbqDFmZNoq5RQEJxMPjzhqbkVQhfBd0VgKjlB46_oh2swSkIpXXZXjyG47zghZ1go_ch9ndD2VLSI7odILqphIbfYMnfiLKW4NcClr-"
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

    fun sendRoomNum(context: Context, roomNum: Long) {
        val params = FeedTemplate
            .newBuilder(
                ContentObject.newBuilder(
                    "마니또를 생성하였습니다.\n초대코드 : $roomNum",
                    DEFAULT_IMG_URL,
                    LinkObject.newBuilder()
                        .setAndroidExecutionParams("$ROOM_NUM=$roomNum")
                        .setIosExecutionParams("$ROOM_NUM=$roomNum")
                        .setWebUrl(BASE_URL)
                        .setMobileWebUrl(BASE_URL)
                        .build()
                ).build()
            )
            .addButton(
                ButtonObject(
                    BTN_TITLE,
                    LinkObject.newBuilder()
                        .setAndroidExecutionParams("$ROOM_NUM=$roomNum")
                        .setIosExecutionParams("$ROOM_NUM=$roomNum")
                        .setWebUrl(BASE_URL)
                        .setMobileWebUrl(BASE_URL)
                        .build()
                )
            )
            .build()

        KakaoLinkService.getInstance().sendDefault(context, params, responseCallBack)
    }

    fun shareMission(context: Context, mission: UserMissionResponse) {
        try {
            val params = FeedTemplate
                .newBuilder(
                    ContentObject.newBuilder(
                        mission.missionName,
                        mission.userMission.missionImg ?: DEFAULT_IMG_URL,
                        LinkObject.newBuilder()
                            .setAndroidExecutionParams("$MISSION_CONTENT=${Gson().toJson(mission)}")
                            .setIosExecutionParams("$MISSION_CONTENT=${Gson().toJson(mission)}")
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
                            .setIosExecutionParams("$MISSION_CONTENT=${Gson().toJson(mission)}")
                            .setMobileWebUrl(BASE_URL)
                            .setWebUrl(BASE_URL)
                            .build()
                    )
                )
                .build()

            KakaoLinkService.getInstance().sendDefault(context, params, responseCallBack)
        } catch (e: KakaoParameterException) {
            e.printStackTrace()
        }
    }
}