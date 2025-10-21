package com.example.vidasalud.model

data class Usuario(
    val nombre: String,
    val correo: String,
    val contraseña: String,
    // Resumen (acumulados o promedios del usuario)
    val pasosTotales: Int = 0,
    val caloriasQuemadas: Int = 0,
    val horasSuenoProm: Double = 0.0,
    val ritmoCardiacoProm: Int = 0
)
