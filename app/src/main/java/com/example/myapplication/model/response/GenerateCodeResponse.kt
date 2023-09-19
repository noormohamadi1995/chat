package com.example.myapplication.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenerateCodeResponse(
    @SerialName("Record") val record : Long? = null,
    @SerialName("Result") val result : String? = null,
    @SerialName("Message") val message : String? = null,
    @SerialName("StatusCode") val statusCode : Int? = null,
    @SerialName("Value") val value : Unit? = null
)
