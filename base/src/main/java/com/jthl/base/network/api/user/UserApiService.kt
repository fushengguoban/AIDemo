package com.jthl.base.network.api.user

import com.squareup.moshi.JsonClass
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * 用户相关的网络请求接口 (包含登录、注册、用户信息等)
 * 将按业务领域划分，而不是按页面划分，避免类爆炸
 */
interface UserApiService {
    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): LoginResponse
    
    // @POST("auth/register")
    // suspend fun register(@Body request: RegisterRequest): RegisterResponse
}

// ============== 数据模型 (统一放在这里或单独的 UserModels.kt 中) ==============

@JsonClass(generateAdapter = true)
data class LoginRequest(
    val email: String,
    val password: String
)

@JsonClass(generateAdapter = true)
data class LoginResponse(
    val token: String,
    val message: String? = null,
    val userId: String? = null,
    val error: String? = null
)
