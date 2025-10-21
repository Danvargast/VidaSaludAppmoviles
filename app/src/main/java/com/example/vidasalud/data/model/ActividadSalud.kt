package com.example.vidasalud.model

data class ActividadSalud(
    val pasos: Int = 0,
    val metaPasos: Int = 10000,
    val calorias: Int = 0,
    val metaCalorias: Int = 1500,
    val horasSueno: Double = 0.0,
    val metaSueno: Double = 8.0,
    val ritmoCardiaco: Int = 0,      // valor actual
    val ritmoCardiacoMeta: Int = 100 // para gauge
)
