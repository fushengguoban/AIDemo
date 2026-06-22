package com.jthl.base.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response

/**
 * 拦截器：负责在每次网络请求的 Header 中自动注入 Token。
 */
class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        
        // TODO: 从 TokenManager 或 DataStore 获取真实的 Token
        val token = TokenManager.getToken()
        
        val requestBuilder = originalRequest.newBuilder()
        if (!token.isNullOrEmpty()) {
            requestBuilder.addHeader("Authorization", "Bearer $token")
        }
        
        return chain.proceed(requestBuilder.build())
    }
}
