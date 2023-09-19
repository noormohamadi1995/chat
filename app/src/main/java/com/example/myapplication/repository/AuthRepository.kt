package com.example.myapplication.repository

import com.example.myapplication.api.ApiClient
import com.example.myapplication.api.EndPoints
import com.example.myapplication.model.request.GenerateCode
import com.example.myapplication.model.response.GenerateCodeResponse
import com.example.myapplication.model.response.LoginResponseModel
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val apiClient: ApiClient
) {
     suspend fun generateCode(phone : String) : GenerateCodeResponse =
          apiClient.getKtorClient().post(EndPoints.generateCode){
             setBody(
                 GenerateCode(
                     phone = phone,
                     parentId = 2,
                     password = ""
                 )
             )
         }.body()

    suspend fun login(phone : String,code : String) : LoginResponseModel =
        apiClient.getKtorClient().post(EndPoints.login){
            setBody(
                GenerateCode(
                    phone = phone,
                    parentId = 2,
                    password = code
                )
            )
        }.body()
}