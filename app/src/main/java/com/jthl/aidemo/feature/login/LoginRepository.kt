package com.jthl.aidemo.feature.login

import com.jthl.aidemo.feature.login.LoginRequest
import com.jthl.aidemo.feature.login.LoginResponse
import com.jthl.aidemo.feature.login.LoginApiService
import com.jthl.base.network.NetworkModule
import com.jthl.base.network.ApiResult
import com.jthl.base.base.BaseRepository

/**
 * 登录仓库接口。
 */
interface LoginRepository {
    suspend fun login(email: String, password: String): ApiResult<LoginResponse>
}

class RealLoginRepository(
    private val apiService: LoginApiService = NetworkModule.retrofit.create(LoginApiService::class.java)
) : BaseRepository(), LoginRepository {
    
    override suspend fun login(email: String, password: String): ApiResult<LoginResponse> {
        // 使用基类的 safeApiCall 执行请求，无需在此处手动编写 try-catch
//        return safeApiCall {
//            val request = LoginRequest(email = email, password = password)
//            apiService.login(request)
//        }
        return if (email == "wang" && password == "123456") {
            ApiResult.Success(
                LoginResponse(
                    token = "mock_token_wang_123456",
                    userId = "888888",
                    error = null
                )
            )
        } else {
            // 如果不满足 Mock 条件，则执行真实的网络请求逻辑（使用 safeApiCall 包装）
            safeApiCall {
                val request = LoginRequest(email = email, password = password)
                apiService.login(request)
            }
        }
    }
}
