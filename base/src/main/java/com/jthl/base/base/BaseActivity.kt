package com.jthl.base.base

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable

/**
 * BaseActivity 为应用程序中的所有 Activity 提供一致的设置。
 * 
 * 在 MVI/Compose 架构中的角色：
 * 1. **标准化**: 确保所有 Activity 都使用 [enableEdgeToEdge]。
 * 2. **减少样板代码**: 子类只需实现 [Content] 方法即可定义其 UI。
 * 3. **一致性**: 集中管理全局配置，如窗口缩进。
 */
abstract class BaseActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // 默认启用全屏显示，以获得现代感十足的界面
        enableEdgeToEdge()
        
        setContent {
            Content()
        }
    }

    /**
     * 子类实现此方法以提供 Composable UI 内容。
     * 此方法在 [AIDemoTheme] 块内被调用。
     */
    @Composable
    abstract fun Content()
}
