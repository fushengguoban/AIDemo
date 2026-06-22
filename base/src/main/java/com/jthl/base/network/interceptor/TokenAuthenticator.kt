package com.jthl.base.network.interceptor

import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

/**
 * 认证器：处理 401 Unauthorized 错误。
 * 在这里可以实现无感刷新 Token 的逻辑，或者在刷新失败时发送全局事件强制用户重新登录。
 */
class TokenAuthenticator : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        // 防止无限循环（如果使用新 Token 请求依然返回 401）
        if (response.request.header("Authorization") != null && response.priorResponse != null) {
            TokenManager.clearToken()
            // TODO: 发送全局事件通知 UI 退出到登录界面
            return null
        }

        // TODO: 在这里执行同步刷新 Token 的请求，获取新的 Token
        // val newToken = refreshToken()
        val newToken = "refreshed_mock_token" // 模拟刷新成功
        TokenManager.saveToken(newToken)

        return response.request.newBuilder()
            .header("Authorization", "Bearer $newToken")
            .build()
    }
}
