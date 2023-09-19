package com.example.myapplication.viewState


data class LoginViewState(
    val phone : String? = null,
    val isSendCode : Boolean = false
)

sealed interface LoginSideEffect{
    class SuccessSendCode(val phone: String) : LoginSideEffect
    data object ErrorSendCode : LoginSideEffect
}