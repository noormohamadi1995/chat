package com.example.myapplication.prefence

import android.content.Context
import android.content.Context.MODE_PRIVATE
import com.example.myapplication.model.response.LoginResponseModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject


class SessionManager @Inject constructor(
    private val json: Json,
   @ApplicationContext private val context: Context
) {

    companion object {
        const val LOGIN = "login"
    }
    private val sh =  context.getSharedPreferences("chat", MODE_PRIVATE)

    fun setLoginState(loginResponseModel: LoginResponseModel) {
        val editor = sh.edit()
        editor.putString(LOGIN, json.encodeToString(loginResponseModel))
        editor.apply()
    }

    fun getLogin() : LoginResponseModel? {
        return sh.getString(LOGIN,null)?.let {
            json.decodeFromString<LoginResponseModel>(it)
        }
    }
}