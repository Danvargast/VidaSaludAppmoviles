package com.example.vidasalud.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable

private val LightColors = lightColorScheme(
    primary = VerdeMenta,
    onPrimary = Blanco,
    secondary = VerdeOscuro,
    onSecondary = Blanco,
    background = GrisClaro,
    surface = Blanco,
    error = ErrorRojo,
    onBackground = GrisTexto,
    onSurface = GrisTexto,
)

@Composable
fun VidaSaludTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColors,
        typography = TipografiaVidaSalud,
        content = content
    )
}
