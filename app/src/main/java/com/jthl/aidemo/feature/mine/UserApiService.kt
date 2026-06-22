package com.jthl.aidemo.feature.mine

import com.jthl.aidemo.feature.mine.UserResponse
import retrofit2.http.GET

/**
 * 用户相关的网络请求接口。
 */
interface UserApiService {

    /**
     * 获取当前登录用户的信息。
     */
    @GET("user/profile")
    suspend fun getUserProfile(): UserResponse
}
