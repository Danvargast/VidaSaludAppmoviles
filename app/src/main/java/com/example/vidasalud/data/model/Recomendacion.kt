package com.example.vidasalud.data.model



import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

data class Recomendacion(
    val id: Int,
    val titulo: String,
    val descripcion: String,
    val icono: ImageVector,
    val colorInicio: Color,
    val colorFin: Color
)
