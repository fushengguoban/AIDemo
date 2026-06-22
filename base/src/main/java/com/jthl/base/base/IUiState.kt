package com.jthl.base.base

/**
 * 通用 UI 状态接口。
 * 强制所有页面的 State 必须包含加载中和错误信息字段，以便自动化处理。
 */
interface IUiState {
    val isLoading: Boolean
    val error: String?

    /**
     * 定义一个通用的复制方法，用于自动化更新加载和错误状态。
     */
    fun copyWith(isLoading: Boolean = this.isLoading, error: String? = this.error): IUiState
}
