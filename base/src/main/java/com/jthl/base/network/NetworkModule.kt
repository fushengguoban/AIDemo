package com.jthl.base.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import com.jthl.base.network.interceptor.AuthInterceptor
import com.jthl.base.network.interceptor.TokenAuthenticator
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object NetworkModule {

    // 基础域名，Retrofit 要求必须以 "/" 结尾
    private const val BASE_URL = "https://smartboard-dh.jintdev.com/"

    private val headerInterceptor: Interceptor by lazy {
        Interceptor { chain ->
            val originalRequest = chain.request()
            val requestBuilder = originalRequest.newBuilder()
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .addHeader("App-Version", "1.0.0")
                .addHeader("Platform", "Android")
            
            chain.proceed(requestBuilder.build())
        }
    }

    private val loggingInterceptor: HttpLoggingInterceptor by lazy {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    private val authInterceptor by lazy { AuthInterceptor() }
    private val tokenAuthenticator by lazy { TokenAuthenticator() }

    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(headerInterceptor)
            .addInterceptor(authInterceptor)
            .authenticator(tokenAuthenticator)
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    /**
     * 创建 API Service 实例的封装方法
     * 示例: val api = NetworkModule.createService(LoginApiService::class.java)
     */
    fun <T> createService(serviceClass: Class<T>): T {
        return retrofit.create(serviceClass)
    }

    /**
     * 内联泛型方法，调用更简洁
     * 示例: val api = NetworkModule.createService<LoginApiService>()
     */
    inline fun <reified T> createService(): T {
        return createService(T::class.java)
    }

}

