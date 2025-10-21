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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.vidasalud.ui.theme.*
import com.example.vidasalud.viewmodel.UsuarioViewModel
import java.util.regex.Pattern

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController, viewModel: UsuarioViewModel) {
    var correo by remember { mutableStateOf("") }
    var contraseña by remember { mutableStateOf("") }
    var error by remember { mutableStateOf<String?>(null) }

    fun validar(): Boolean {
        if (correo.isBlank() || contraseña.isBlank()) {
            error = "Ingresa tu correo y contraseña."
            return false
        }
        val correoRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"
        if (!Pattern.matches(correoRegex, correo)) {
            error = "Formato de correo no válido."
            return false
        }
        error = null
        return true
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Iniciar sesión", color = Blanco) },
                navigationIcon = {
                    IconButton(onClick = { /* opcional: navController.popBackStack() */ }) {
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
                    Brush.verticalGradient(listOf(Color(0xFFE8FFF7), Color(0xFFFFFFFF)))
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
                    Text("Bienvenido a VidaSalud", color = VerdeOscuro, fontSize = 22.sp)
                    Spacer(Modifier.height(20.dp))

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

                    OutlinedTextField(
                        value = contraseña,
                        onValueChange = { contraseña = it },
                        label = { Text("Contraseña") },
                        singleLine = true,
                        visualTransformation = PasswordVisualTransformation(),
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = VerdeMenta,
                            focusedLabelColor = VerdeMenta
                        )
                    )

                    Spacer(Modifier.height(10.dp))

                    if (error != null) {
                        Text(error!!, color = Color.Red, fontSize = 13.sp)
                        Spacer(Modifier.height(6.dp))
                    }

                    Button(
                        onClick = {
                            if (validar()) {
                                val ok = viewModel.login(correo, contraseña)
                                if (ok) {
                                    navController.navigate("principal") {
                                        popUpTo("login") { inclusive = true }
                                    }
                                } else {
                                    error = "Credenciales inválidas."
                                }
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = VerdeMenta),
                        shape = RoundedCornerShape(25.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                    ) {
                        Text("Entrar", color = Blanco)
                    }

                    Spacer(Modifier.height(12.dp))

                    TextButton(onClick = { navController.navigate("registro") }) {
                        Text("¿No tienes cuenta? Regístrate", color = VerdeMenta)
                    }
                }
            }
        }
    }
}
