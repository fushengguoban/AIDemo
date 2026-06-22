package com.jthl.aidemo.navigation

import kotlinx.serialization.Serializable
import androidx.navigation3.runtime.NavKey

/**
 * 登录界面导航键。
 */
@Serializable
data object Login : NavKey

/**
 * 主界面导航键。
 */
@Serializable
data object Home : NavKey

/**
 * “我的”个人中心界面导航键。
 */
@Serializable
data object Mine : NavKey

/**
 * 线路详情界面
 */
@Serializable
data object Bus: NavKey
