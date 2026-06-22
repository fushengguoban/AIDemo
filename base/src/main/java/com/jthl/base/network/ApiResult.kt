package com.jthl.base.network

/**
 * 通用的网络请求结果封装类。
 * 使用 sealed class 确保结果处理的完备性。
 */
sealed class ApiResult<out T> {
    
    /** 成功状态，包含解析后的数据 */
    data class Success<out T>(val data: T) : ApiResult<T>()

    /** 业务错误状态，包含错误码和错误信息 */
    data class Error(val code: Int, val message: String) : ApiResult<Nothing>()

    /** 异常状态，包含抛出的异常信息 */
    data class Exception(val throwable: Throwable) : ApiResult<Nothing>()
}
