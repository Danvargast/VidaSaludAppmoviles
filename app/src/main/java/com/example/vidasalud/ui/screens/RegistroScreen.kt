

package com.example.vidasalud.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.vidasalud.ui.theme.*
import com.example.vidasalud.viewmodel.UsuarioViewModel
import java.util.regex.Pattern

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun RegistroScreen(navController: NavController, viewModel: UsuarioViewModel)
 {
    // Campos del formulario
    var nombre by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }
    var confirmarContrasena by remember { mutableStateOf("") }

    // Mensajes de error
    var errorMensaje by remember { mutableStateOf<String?>(null) }

    // Validación general
    fun validarCampos(): Boolean {
        // 1️⃣ Vacíos
        if (nombre.isBlank() || correo.isBlank() || contrasena.isBlank() || confirmarContrasena.isBlank()) {
            errorMensaje = "Por favor completa todos los campos."
            return false
        }

        // 2️⃣ Correo válido
        val correoRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"
        if (!Pattern.matches(correoRegex, correo)) {
            errorMensaje = "El correo electrónico no es válido."
            return false
        }

        // 3️⃣ Contraseña mínima
        if (contrasena.length < 6) {
            errorMensaje = "La contraseña debe tener al menos 6 caracteres."
            return false
        }

        // 4️⃣ Coincidencia
        if (contrasena != confirmarContrasena) {
            errorMensaje = "Las contraseñas no coinciden."
            return false
        }

        // ✅ Todo correcto
        errorMensaje = null
        return true
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Crear cuenta", color = Blanco) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Volver", tint = Blanco)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = VerdeMenta)
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        listOf(Color(0xFFE8FFF7), Color(0xFFFFFFFF))
                    )
                )
                .padding(padding),
            contentAlignment = Alignment.Center
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(12.dp),
                shape = RoundedCornerShape(20.dp),
                elevation = CardDefaults.cardElevation(8.dp),
                colors = CardDefaults.cardColors(containerColor = Blanco)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        "Regístrate en VidaSalud",
                        fontWeight = FontWeight.Bold,
                        color = VerdeOscuro,
                        fontSize = 22.sp
                    )

                    Spacer(Modifier.height(20.dp))

                    // Campo nombre
                    OutlinedTextField(
                        value = nombre,
                        onValueChange = { nombre = it },
                        label = { Text("Nombre completo") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = VerdeMenta,
                            focusedLabelColor = VerdeMenta
                        )
                    )

                    Spacer(Modifier.height(12.dp))

                    // Campo correo
                    OutlinedTextField(
                        value = correo,
                        onValueChange = { correo = it },
                        label = { Text("Correo electrónico") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = VerdeMenta,
                            focusedLabelColor = VerdeMenta
                        )
                    )

                    Spacer(Modifier.height(12.dp))

                    // Campo contraseña
                    OutlinedTextField(
                        value = contrasena,
                        onValueChange = { contrasena = it },
                        label = { Text("Contraseña") },
                        singleLine = true,
                        visualTransformation = PasswordVisualTransformation(),
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = VerdeMenta,
                            focusedLabelColor = VerdeMenta
                        )
                    )

                    Spacer(Modifier.height(12.dp))

                    // Confirmar contraseña
                    OutlinedTextField(
                        value = confirmarContrasena,
                        onValueChange = { confirmarContrasena = it },
                        label = { Text("Confirmar contraseña") },
                        singleLine = true,
                        visualTransformation = PasswordVisualTransformation(),
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = VerdeMenta,
                            focusedLabelColor = VerdeMenta
                        )
                    )

                    Spacer(Modifier.height(18.dp))

                    // Mensaje de error
                    if (errorMensaje != null) {
                        Text(
                            errorMensaje!!,
                            color = Color.Red,
                            fontSize = 13.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(bottom = 10.dp)
                        )
                    }

                    // Botón registrarse
                    Button(
                        onClick = {
                            if (validarCampos()) {
                                navController.navigate("login")
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = VerdeMenta),
                        shape = RoundedCornerShape(25.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                    ) {
                        Text("Registrarse", fontWeight = FontWeight.Bold, color = Blanco)
                    }

                    Spacer(Modifier.height(14.dp))

                    TextButton(onClick = { navController.popBackStack() }) {
                        Text(
                            "¿Ya tienes una cuenta? Inicia sesión",
                            color = VerdeMenta,
                            fontWeight = FontWeight.SemiBold
                        )
                        if (validarCampos()) {
                            val ok = viewModel.registrar(nombre, correo, contrasena)
                            if (ok) {
                                // Registro correcto: vuelve al login
                                navController.navigate("login") { popUpTo("login") { inclusive = false } }
                            } else {
                                errorMensaje = "El correo ya está registrado."
                            }
                        }

                    }
                }
            }
        }
    }
}
