package com.example.vidasalud.viewmodel



import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.example.vidasalud.model.ActividadSalud
import kotlin.math.max
import kotlin.math.min

class HealthViewModel : ViewModel() {

    var actividad by mutableStateOf(ActividadSalud())
        private set

    // Controles para demo/simulación
    fun sumarPasos(delta: Int) {
        val nuevo = (actividad.pasos + delta).coerceIn(0, 50_000)
        actividad = actividad.copy(pasos = nuevo)
    }

    fun setCalorias(valor: Int) {
        actividad = actividad.copy(calorias = valor.coerceAtLeast(0))
    }

    fun setSueno(horas: Double) {
        actividad = actividad.copy(horasSueno = min(24.0, max(0.0, horas)))
    }

    fun setRitmo(bpm: Int) {
        actividad = actividad.copy(ritmoCardiaco = bpm.coerceIn(30, 220))
    }
}
