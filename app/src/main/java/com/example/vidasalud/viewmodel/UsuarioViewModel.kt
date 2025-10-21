package com.example.vidasalud.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

data class Usuario(val nombre: String, val correo: String, val contraseña: String)

class UsuarioViewModel : ViewModel() {

    private val usuarios = mutableStateListOf<Usuario>()

    // 👇 Usuario actualmente autenticado (observado por Compose)
    var currentUser by mutableStateOf<Usuario?>(null)
        private set

    fun registrar(nombre: String, correo: String, contraseña: String): Boolean {
        if (usuarios.any { it.correo.equals(correo, ignoreCase = true) }) return false
        val nuevo = Usuario(nombre, correo, contraseña)
        usuarios.add(nuevo)
        return true
    }

    fun login(correo: String, contraseña: String): Boolean {
        val user = usuarios.find { it.correo.equals(correo, ignoreCase = true) && it.contraseña == contraseña }
        currentUser = user // 👈 guardamos el usuario autenticado (o null si no coincide)
        return user != null
    }

    fun logout() {
        currentUser = null
    }
}
