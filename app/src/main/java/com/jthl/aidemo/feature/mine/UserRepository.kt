package com.jthl.aidemo.feature.mine

import com.jthl.aidemo.feature.mine.UserResponse
import com.jthl.base.network.NetworkModule
import com.jthl.aidemo.feature.mine.UserApiService
import com.jthl.base.network.ApiResult
import com.jthl.base.base.BaseRepository
import kotlinx.coroutines.delay

/**
 * 用户数据仓库接口。
 */
interface UserRepository {
    suspend fun getUserProfile(): ApiResult<UserResponse>
}

class RealUserRepository(
    private val apiService: UserApiService = NetworkModule.retrofit.create(UserApiService::class.java)
) : BaseRepository(), UserRepository {

    override suspend fun getUserProfile(): ApiResult<UserResponse> {
        // 在真实环境下调用 safeApiCall { apiService.getUserProfile() }
        // 为了演示，我们在这里模拟一个成功的返回
        return safeApiCall {
            // 模拟网络延迟
            delay(1000)
            
            // 返回模拟数据
            UserResponse(
                id = "AI_007",
                name = "智能助手",
                avatar = "https://example.com/avatar.png",
                email = "user@ai.com",
                bio = "正在探索 AI 世界的开发者"
            )
        }
    }
}
