package com.sarathexp.offlinewallet.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import com.sarathexp.offlinewallet.app.theme.OfflineWalletTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OfflineWalletTheme {
                SystemBarsColor()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen(
                        modifier = Modifier.fillMaxSize(),
                    )
                }
            }
        }
    }

    @Composable
    private fun SystemBarsColor() {
        val bgColor = MaterialTheme.colorScheme.background.toArgb()
        LaunchedEffect(Unit) {
            enableEdgeToEdge(
                statusBarStyle = SystemBarStyle.auto(bgColor, bgColor),
                navigationBarStyle =
                    SystemBarStyle.auto(
                        bgColor,
                        bgColor,
                    ),
            )
        }
    }
}
