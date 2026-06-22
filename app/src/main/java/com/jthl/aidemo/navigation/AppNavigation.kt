package com.jthl.aidemo.navigation

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.jthl.aidemo.feature.bus.BusRouteScreen
import com.jthl.aidemo.feature.home.HomeScreen
import com.jthl.aidemo.feature.login.LoginScreen
import com.jthl.aidemo.feature.login.LoginViewModel
import com.jthl.aidemo.feature.mine.MineScreen
import com.jthl.aidemo.feature.mine.MineViewModel

@Composable
fun AppNavigation() {
    val backStack = rememberNavBackStack(Login)

    val provider = entryProvider {
        // 登录页
        entry<Login> {
            val loginViewModel: LoginViewModel = viewModel()
            LoginScreen(
                viewModel = loginViewModel,
                onNavigateToHome = {
                    backStack.clear()
                    backStack.add(Home)
                }
            )
        }
        // 首页
        entry<Home> {
            HomeScreen(onNavigateToMine = {
                backStack.add(Bus)
            })
        }
        // 个人中心页
        entry<Mine> {
            val mineViewModel: MineViewModel = viewModel()
            MineScreen(
                viewModel = mineViewModel,
                onNavigateToLogin = {
                    backStack.clear()
                    backStack.add(Login)
                }
            )
        }
        // 线路详情界面
        entry<Bus> {
            BusRouteScreen()
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        contentWindowInsets = androidx.compose.foundation.layout.WindowInsets(0, 0, 0, 0)
    ) { innerPadding ->
        NavDisplay(
            backStack = backStack,
            modifier = Modifier.padding(innerPadding),
            onBack = { backStack.removeLastOrNull() },
            entryProvider = provider,
            transitionSpec = {
                slideInHorizontally(initialOffsetX = { it }) togetherWith
                    slideOutHorizontally(targetOffsetX = { -it })
            },
            popTransitionSpec = {
                slideInHorizontally(initialOffsetX = { -it }) togetherWith
                    slideOutHorizontally(targetOffsetX = { it })
            }
        )
    }
}
