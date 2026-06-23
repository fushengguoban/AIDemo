package com.jthl.aidemo.feature.login

import com.jthl.base.network.api.user.UserRepository
import com.jthl.base.network.launchRequest
import com.jthl.base.base.BaseViewModel

/**
 * 终极重构后的登录 ViewModel。
 * 1. 移除了 onLoading 和 onError 的手动处理。
 * 2. 移除了 viewModelScope 的手动启动。
 */
class LoginViewModel(
    private val repository: UserRepository = UserRepository()
) : BaseViewModel<LoginState, LoginIntent, LoginEffect>(LoginState()) {

    override fun onIntent(intent: LoginIntent) {
        when (intent) {
            is LoginIntent.EmailChanged -> updateState { copy(email = intent.email) }
            is LoginIntent.PasswordChanged -> updateState { copy(password = intent.password) }
            is LoginIntent.LoginClicked -> performLogin()
            is LoginIntent.ErrorDismissed -> updateState { copy(error = null) }
        }
    }

    private fun performLogin() {
        val currentState = state.value
        if (currentState.email.isBlank() || currentState.password.isBlank()) {
            updateState { copy(error = "电子邮箱和密码不能为空") }
            return
        }

        // 现在只需提供 block 和 onSuccess。
        // 加载态切换和通用错误分发已由扩展方法自动完成。
        launchRequest(
            block = { repository.login(currentState.email, currentState.password) },
            onSuccess = { _ ->
                updateState { copy(isLoginSuccessful = true) }
                emitEffect(LoginEffect.NavigateToHome)
            }
        )
    }
}
