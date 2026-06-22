package com.jthl.aidemo.feature.login

import com.jthl.base.base.IUiState

/**
 * 实现了 IUiState 的登录状态类。
 */
data class LoginState(
    val email: String = "",
    val password: String = "",
    override val isLoading: Boolean = false,
    override val error: String? = null,
    val isLoginSuccessful: Boolean = false
) : IUiState {
    override fun copyWith(isLoading: Boolean, error: String?): IUiState {
        return copy(isLoading = isLoading, error = error)
    }
}

sealed interface LoginIntent {
    data class EmailChanged(val email: String) : LoginIntent
    data class PasswordChanged(val password: String) : LoginIntent
    data object LoginClicked : LoginIntent
    data object ErrorDismissed : LoginIntent
}

sealed interface LoginEffect {
    data object NavigateToHome : LoginEffect
}
