package com.jthl.base.network.interceptor

/**
 * 简单的 Token 管理器。
 * 实际项目中应替换为使用 DataStore 或 SharedPreferences 存储的逻辑。
 */
object TokenManager {
    private var currentToken: String? = null

    fun getToken(): String? = currentToken

    fun saveToken(token: String) {
        currentToken = token
    }

    fun clearToken() {
        currentToken = null
    }
}
