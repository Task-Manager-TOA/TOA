package ru.fursa.toa

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import ru.fursa.toa.core.ui.theme.TOATheme
import ru.fursa.toa.login.presentation.LoginScreen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TOATheme {
                LoginScreen(onLoginCompleted = {
                    Log.d("MainActivity", "Success login")
                })
            }
        }
    }
}
