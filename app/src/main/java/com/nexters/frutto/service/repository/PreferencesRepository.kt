package com.nexters.frutto.service.repository

import android.content.Context
import com.nexters.frutto.service.model.Applicant
import com.google.gson.Gson

class PreferencesRepository(context: Context) {
    companion object {
        private const val PREFERENCES_LOGIN = "login"

        private const val DEFAULT_VALUE_STRING = ""
        private const val DEFAULT_VALUE_INT = 0
        private const val DEFAULT_VALUE_LONG = 0L
        private const val DEFAULT_VALUE_BOOLEAN_FALSE = false
        private const val DEFAULT_VALUE_BOOLEAN_TRUE = true

        private const val KEY_USER_ID = "kakao_token"
        private const val KEY_USER_NAME = "kakao_nickname"
        private const val KEY_USER_MANITO_NAME = "manito_name"
        private const val KEY_USER_LIST = "user_list_of_"
        private const val KEY_USER_FRUTTO_ID = "frutto_id"
        private const val KEY_USER_MANITO_FRUTTO_ID = "manito_frutto_id"
        private const val KEY_USER_ROOM_ID = "roomId"
        private const val KEY_USER_IS_MANAGER = "isManager"
        private const val KEY_CHECK_NITO = "checkNito"
        private const val KEY_CHECK_NAETO = "checkNaeto"
        private const val KEY_ON_NOTIFICATION = "on_notification"
    }

    private val sf = context.getSharedPreferences(PREFERENCES_LOGIN, Context.MODE_PRIVATE)
    private val editor = sf.edit()

    fun setUserId(userId: String?) {
        editor.putString(KEY_USER_ID, userId)
        editor.commit()
    }

    fun setUserNm(userNm: String?) {
        editor.putString(KEY_USER_NAME, userNm)
        editor.commit()
    }

    fun setManitoNm(manitoNm: String?) {
        editor.putString(KEY_USER_MANITO_NAME, manitoNm)
        editor.commit()
    }

    fun setUserList(_userList: ArrayList<Applicant>) {
        val gson = Gson()
        val userrList = gson.toJson(_userList)
        editor.putString("${KEY_USER_LIST}${getRoomId()}", userrList)
        editor.commit()
    }

    fun setFruttoId(fruttoId: Int?) {
        editor.putInt(KEY_USER_FRUTTO_ID, fruttoId ?: DEFAULT_VALUE_INT)
        editor.commit()
    }

    fun setManitoFruttoId(manitoFruttoId: Int?) {
        editor.putInt(KEY_USER_MANITO_FRUTTO_ID, manitoFruttoId ?: DEFAULT_VALUE_INT)
        editor.commit()
    }

    fun setRoomId(roomId: Long?) {
        editor.putLong(KEY_USER_ROOM_ID, roomId ?: DEFAULT_VALUE_LONG)
        editor.commit()
    }

    fun setIsManager(isManager: Boolean?) {
        editor.putBoolean(KEY_USER_IS_MANAGER, isManager ?: DEFAULT_VALUE_BOOLEAN_FALSE)
        editor.commit()
    }

    fun setOnNotification(on: Boolean) {
        editor.putBoolean(KEY_ON_NOTIFICATION, on)
        editor.commit()
    }

    fun setCheckNito() {
        editor.putBoolean(KEY_CHECK_NITO, true)
        editor.commit()
    }

    fun setCheckNaeto() {
        editor.putBoolean(KEY_CHECK_NAETO, true)
        editor.commit()
    }

    fun test() {
        editor.putBoolean(KEY_CHECK_NITO, false)
        editor.putBoolean(KEY_CHECK_NAETO, false)
        editor.commit()
    }

    fun getUserId() = sf.getString(KEY_USER_ID, DEFAULT_VALUE_STRING)
    fun getUserNm() = sf.getString(KEY_USER_NAME, DEFAULT_VALUE_STRING)
    fun getManitoNm() = sf.getString(KEY_USER_MANITO_NAME, DEFAULT_VALUE_STRING)
    fun getFruttoId() = sf.getInt(KEY_USER_FRUTTO_ID, DEFAULT_VALUE_INT)
    fun getManitoFruttoId() = sf.getInt(KEY_USER_MANITO_FRUTTO_ID, DEFAULT_VALUE_INT)
    fun getRoomId() = sf.getLong(KEY_USER_ROOM_ID, DEFAULT_VALUE_LONG)
    fun getIsManager() = sf.getBoolean(KEY_USER_IS_MANAGER, DEFAULT_VALUE_BOOLEAN_FALSE)
    fun getCheckNito() = sf.getBoolean(KEY_CHECK_NITO, DEFAULT_VALUE_BOOLEAN_FALSE)
    fun getCheckNaeto() = sf.getBoolean(KEY_CHECK_NAETO, DEFAULT_VALUE_BOOLEAN_FALSE)
    fun getOnNotification() = sf.getBoolean(KEY_ON_NOTIFICATION, DEFAULT_VALUE_BOOLEAN_TRUE)
    fun getUserList(): ArrayList<Applicant> {
        val data = sf.getString("${KEY_USER_LIST}${getRoomId()}", DEFAULT_VALUE_STRING)
        return ArrayList(Gson().fromJson(data, Array<Applicant>::class.java).toList())
    }

    fun clearManitoData() {
        editor.remove("${KEY_USER_LIST}${getRoomId()}")
        editor.remove(KEY_USER_MANITO_NAME)
        editor.remove(KEY_USER_FRUTTO_ID)
        editor.remove(KEY_USER_MANITO_FRUTTO_ID)
        editor.remove(KEY_USER_ROOM_ID)
        editor.remove(KEY_USER_IS_MANAGER)
        editor.remove(KEY_CHECK_NITO)
        editor.remove(KEY_CHECK_NAETO)
        editor.commit()
    }

    fun clearAll() {
        editor.clear()
        editor.commit()
    }
}