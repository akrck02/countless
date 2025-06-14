package org.akrck02.countless

import android.app.Activity
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Window
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.FragmentActivity
import org.akrck02.countless.module.appModule
import org.akrck02.countless.module.initKoin
import org.akrck02.countless.ui.theme.getSystemThemeColors
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.compose.KoinContext
import org.koin.core.component.KoinComponent
import org.koin.core.context.stopKoin
import kotlin.and

class MainActivity : FragmentActivity(), KoinComponent {
    @RequiresApi(Build.VERSION_CODES.R)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initKoin {
            androidLogger()
            androidContext(this@MainActivity)
            modules(appModule)
        }

        setContent {
            val colors = getSystemThemeColors()
            enableEdgeToEdge(navigationBarStyle = SystemBarStyle.dark(colors.surface.toArgb()))
            App()
        }
    }

}

@RequiresApi(Build.VERSION_CODES.R)
@Preview
@Composable
fun AppAndroidPreview() {
    App()
}

