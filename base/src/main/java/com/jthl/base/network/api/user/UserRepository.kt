package com.jthl.base.network.api.user

import com.jthl.base.network.NetworkModule
import com.jthl.base.network.ApiResult
import com.jthl.base.base.BaseRepository

/**
 * 用户域的数据仓库，处理所有用户相关的网络请求 (登录、注册、获取信息等)
 */
class UserRepository(
    private val apiService: UserApiService = NetworkModule.createService()
) : BaseRepository() {
    
    suspend fun login(email: String, password: String): ApiResult<LoginResponse> {
        return safeApiCall {
            apiService.login(LoginRequest(email = email, password = password))
        }
    }
    
    // suspend fun register(...) = safeApiCall { ... }
}
