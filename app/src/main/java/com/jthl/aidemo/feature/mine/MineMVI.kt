package com.jthl.aidemo.feature.mine

import com.jthl.aidemo.feature.mine.UserResponse
import com.jthl.base.base.IUiState

/**
 * 实现了 IUiState 的“我的”状态类。
 */
data class MineState(
    val user: UserResponse? = null,
    override val isLoading: Boolean = false,
    override val error: String? = null
) : IUiState {
    override fun copyWith(isLoading: Boolean, error: String?): IUiState {
        return copy(isLoading = isLoading, error = error)
    }
}

sealed interface MineIntent {
    data object LoadUserProfile : MineIntent
    data object LogoutClicked : MineIntent
    data object Refresh : MineIntent
}

sealed interface MineEffect {
    data object NavigateToLogin : MineEffect
    data class ShowToast(val message: String) : MineEffect
}
