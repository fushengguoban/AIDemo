package com.jthl.aidemo.feature.login

import com.jthl.aidemo.feature.login.LoginRequest
import com.jthl.aidemo.feature.login.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * 登录相关的网络请求接口。
 */
interface LoginApiService {

    /**
     * 执行登录请求。
     * 您后续只需修改 @POST 中的接口路径即可。
     */
    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): LoginResponse
}
