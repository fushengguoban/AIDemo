package com.jthl.base.network

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jthl.base.base.BaseViewModel
import com.jthl.base.base.IUiState
import kotlinx.coroutines.launch

/**
 * 【自动化请求扩展】：针对 ViewModel 的封装。
 * 如果 State 实现了 IUiState，则自动处理加载和错误逻辑。
 */
@Suppress("UNCHECKED_CAST")
fun <S, I, E, T> BaseViewModel<S, I, E>.launchRequest(
    block: suspend () -> ApiResult<T>,
    onSuccess: (T) -> Unit
) where S : IUiState {
    viewModelScope.launch {
        // 自动开启加载态
        updateState { copyWith(isLoading = true, error = null) as S }
        
        when (val result = block()) {
            is ApiResult.Success -> {
                // 自动关闭加载态并执行成功回调
                updateState { copyWith(isLoading = false) as S }
                onSuccess(result.data)
            }
            is ApiResult.Error -> {
                // 自动关闭加载态并自动填入错误信息
                updateState { copyWith(isLoading = false, error = result.message) as S }
            }
            is ApiResult.Exception -> {
                // 自动关闭加载态并处理系统异常
                updateState { copyWith(isLoading = false, error = result.throwable.localizedMessage) as S }
            }
        }
    }
}
