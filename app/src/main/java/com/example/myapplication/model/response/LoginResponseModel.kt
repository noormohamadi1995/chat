package com.example.myapplication.model.response

import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class LoginResponseModel(
    @SerialName("Record") val record : LoginRecord? = null,
    @SerialName("Result") val result : String? = null,
    @SerialName("Message") val message : String? = null,
    @SerialName("StatusCode") val statusCode : Int? = null,
    @SerialName("Value") val value : Unit? = null
)


@Serializable
data class LoginRecord(
    @SerialName("UserId") val userId : Long? = null,
    @SerialName("FirstName") val firstName : String? = null,
    @SerialName("LastName") val lastName : String? = null,
    @SerialName("Username") val username : String? = null,
    @SerialName("ConsoleToken") val consoleToken : String? = null,
    @SerialName("AutomationToken") val automationToken : String? = null,
    @SerialName("InstitutionToken") val institutionToken : String? = null,
    @SerialName("LibraryToken") val libraryToken : String? = null,
    @SerialName("PayingWagesToken") val payingWagesToken : String? = null,
    @SerialName("PublicToken") val publicToken : String? = null,
    @SerialName("StoreToken") val storeToken : String? = null,
    @SerialName("AdvertisementToken") val advertisementToken : String? = null,
    @SerialName("MonitoringToken") val monitoringToken : String? = null,
    @SerialName("PushNotificationsToken") val pushNotificationToken : String? = null,
    @SerialName("NoticeToken") val noticeToken : String? = null,
    @SerialName("ChatToken") val chatToken : String? = null,
     @SerialName("SmsLogToken") val smsLogToken : String? = null,
    @SerialName("DateTimeNow") val dateTimeNow : String? = null,
    @SerialName("ExpireDate") val expireDate : String? = null
)
