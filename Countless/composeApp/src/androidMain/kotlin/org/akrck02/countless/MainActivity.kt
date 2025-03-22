package org.akrck02.countless

import android.os.Build
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.FragmentActivity
import org.akrck02.countless.dal.AndroidDataAccessLayer

class MainActivity : FragmentActivity() {
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val dataAccess = AndroidDataAccessLayer(LocalContext.current)
            App(dataAccess)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.R)
@Preview
@Composable
fun AppAndroidPreview() {
    val dataAccess = AndroidDataAccessLayer(LocalContext.current)
    App(dataAccess)
}