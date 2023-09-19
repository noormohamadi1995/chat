package com.example.myapplication.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenerateCode(
    @SerialName("UserName") val phone : String? = null,
    @SerialName("ParentId") val parentId : Int? = null,
    @SerialName("Password") val password : String? = null
)
