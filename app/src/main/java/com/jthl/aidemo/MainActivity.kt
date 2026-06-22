package com.jthl.aidemo

import androidx.compose.runtime.Composable
import com.jthl.aidemo.navigation.AppNavigation
import com.jthl.base.base.BaseActivity

import com.jthl.aidemo.core.theme.AIDemoTheme

/**
 * MainActivity 是 AI Demo 应用程序的单 Activity 入口点。
 */
class MainActivity : BaseActivity() {

    @Composable
    override fun Content() {
        AIDemoTheme {
            AppNavigation()
        }
    }
}
