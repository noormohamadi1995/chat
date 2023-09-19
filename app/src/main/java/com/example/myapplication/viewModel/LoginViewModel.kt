package com.example.myapplication.viewModel

import androidx.lifecycle.ViewModel
import com.example.myapplication.prefence.SessionManager
import com.example.myapplication.repository.AuthRepository
import com.example.myapplication.viewState.LoginSideEffect
import com.example.myapplication.viewState.LoginViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val sessionManager: SessionManager
) : ViewModel(), ContainerHost<LoginViewState, LoginSideEffect> {
    override val container: Container<LoginViewState, LoginSideEffect> =
        container(initialState = LoginViewState())

    fun generateCode(phone: String) = intent {
        val result = kotlin.runCatching {
            authRepository.generateCode(phone)
        }
        if (result.isSuccess) {
            postSideEffect(sideEffect = LoginSideEffect.SuccessSendCode(phone))
            reduce {
                state.copy(
                    phone = phone,
                    isSendCode = true
                )
            }
        } else {
            postSideEffect(sideEffect = LoginSideEffect.ErrorSendCode)
        }
    }

    fun login(phone: String, code: String) = intent {
        val res = kotlin.runCatching {
            authRepository.login(phone, code)
        }
        if (res.isSuccess){
            res.getOrNull()?.let {login->
                login.record?.let {
                        sessionManager.setLoginState(login)
                    postSideEffect(sideEffect = LoginSideEffect.SuccessSendCode(phone))
                } ?: kotlin.run {
                    postSideEffect(sideEffect = LoginSideEffect.ErrorSendCode)
                }
            }
        }
    }
}