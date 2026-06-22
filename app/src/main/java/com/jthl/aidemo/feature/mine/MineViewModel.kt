package com.jthl.aidemo.feature.mine

import com.jthl.aidemo.feature.mine.RealUserRepository
import com.jthl.aidemo.feature.mine.UserRepository
import com.jthl.base.network.launchRequest
import com.jthl.base.base.BaseViewModel

/**
 * 终极重构后的“我的”模块 ViewModel。
 */
class MineViewModel(
    private val repository: UserRepository = RealUserRepository()
) : BaseViewModel<MineState, MineIntent, MineEffect>(MineState()) {

    override fun onIntent(intent: MineIntent) {
        when (intent) {
            is MineIntent.LoadUserProfile -> fetchUserProfile()
            is MineIntent.Refresh -> fetchUserProfile()
            is MineIntent.LogoutClicked -> emitEffect(MineEffect.NavigateToLogin)
        }
    }

    private fun fetchUserProfile() {
        // 全自动处理加载状态及错误信息更新。
        launchRequest(
            block = { repository.getUserProfile() },
            onSuccess = { user -> 
                updateState { copy(user = user) }
            }
        )
    }
}
