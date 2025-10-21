package com.example.vidasalud

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.vidasalud.ui.navigation.NavGraph
import com.example.vidasalud.ui.theme.VidaSaludTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VidaSaludTheme {
                NavGraph()
            }
        }
    }
}
