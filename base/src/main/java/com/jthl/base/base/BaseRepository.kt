package com.jthl.base.base

import com.jthl.base.network.ApiResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

/**
 * 基础仓库类，提供通用的网络请求安全调用逻辑。
 * 集中处理 HTTP 错误、网络连接异常等。
 */
abstract class BaseRepository {

    /**
     * 安全地执行 API 调用，并将其封装为 [ApiResult]。
     * 自动切换到 [Dispatchers.IO] 线程执行。
     */
    protected suspend fun <T> safeApiCall(apiCall: suspend () -> T): ApiResult<T> {
        return withContext(Dispatchers.IO) {
            try {
                ApiResult.Success(apiCall())
            } catch (e: HttpException) {
                // 处理 HTTP 错误 (4xx, 5xx)
                ApiResult.Error(e.code(), mapHttpErrorMessage(e))
            } catch (e: IOException) {
                // 处理网络连接/超时问题
                ApiResult.Error(-1, "无法连接到服务器，请检查网络设置")
            } catch (e: Exception) {
                // 处理其他未知异常
                ApiResult.Exception(e)
            }
        }
    }

    /**
     * 将 HTTP 状态码映射为用户友好的中文提示。
     */
    private fun mapHttpErrorMessage(e: HttpException): String {
        return when (e.code()) {
            401 -> "身份验证失败，请重新登录"
            404 -> "请求地址不存在"
            500 -> "服务器内部错误"
            else -> "请求失败 (${e.code()})"
        }
    }
}
