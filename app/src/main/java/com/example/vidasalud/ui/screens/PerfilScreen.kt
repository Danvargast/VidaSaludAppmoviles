@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.vidasalud.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.vidasalud.ui.theme.*
import com.example.vidasalud.viewmodel.UsuarioViewModel

@Composable
fun PerfilScreen(navController: NavController, viewModel: UsuarioViewModel) {
    val user = viewModel.currentUser

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mi Perfil", color = Blanco) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Volver", tint = Blanco)
                    }
                },
                actions = {
                    IconButton(onClick = { navController.navigate("configuracion") }) {
                        Icon(Icons.Filled.Settings, contentDescription = "Configuración", tint = Blanco)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = VerdeMenta)
            )
        },
        containerColor = GrisClaro
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        listOf(Color(0xFFE8FFF7), Color(0xFFF6F6F6))
                    )
                )
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Avatar
            Box(
                modifier = Modifier
                    .size(110.dp)
                    .clip(CircleShape)
                    .background(VerdeMenta),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    Icons.Filled.Person,
                    contentDescription = "Usuario",
                    tint = Blanco,
                    modifier = Modifier.size(70.dp)
                )
            }

            Spacer(Modifier.height(12.dp))
            Text(
                user?.nombre ?: "Invitado",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = VerdeOscuro
            )
            Text(user?.correo ?: "—", color = GrisTexto, fontSize = 15.sp)

            Spacer(Modifier.height(26.dp))

            // 🧾 Tarjeta con resumen general
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = Blanco),
                elevation = CardDefaults.cardElevation(8.dp)
            ) {
                Column(
                    Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        "Resumen de tu progreso",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = VerdeOscuro
                    )
                    Spacer(Modifier.height(14.dp))
                    // Valores de ejemplo (puedes sustituir por datos reales cuando los tengas)
                    EstadisticaPerfil("🏃 Total de pasos", "54.320")
                    EstadisticaPerfil("🔥 Calorías quemadas", "12.540 kcal")
                    EstadisticaPerfil("😴 Sueño promedio", "7.2 h")
                    EstadisticaPerfil("❤️ Frecuencia cardíaca", "76 bpm")
                }
            }

            Spacer(Modifier.height(26.dp))

            // Tarjeta motivacional (opcional)
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = Blanco),
                border = BorderStroke(1.dp, VerdeMenta.copy(alpha = 0.5f)),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(
                    Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        "✨ ¡Sigue así!",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = VerdeMenta
                    )
                    Spacer(Modifier.height(6.dp))
                    Text(
                        "Estás logrando grandes avances en tu bienestar físico y mental.",
                        color = GrisTexto,
                        fontSize = 14.sp
                    )
                }
            }

            Spacer(Modifier.height(32.dp))

            // Cerrar sesión
            Button(
                onClick = {
                    viewModel.logout()
                    navController.navigate("login") {
                        popUpTo("principal") { inclusive = true }
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = VerdeMenta),
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(50.dp),
                shape = RoundedCornerShape(25.dp)
            ) {
                Text(
                    "Cerrar sesión",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    color = Blanco
                )
            }

            Spacer(Modifier.height(10.dp))
            Text(
                "Versión 1.0.0",
                color = GrisTexto.copy(alpha = 0.6f),
                fontSize = 12.sp
            )
        }
    }
}

@Composable
fun EstadisticaPerfil(titulo: String, valor: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(titulo, color = GrisTexto)
        Text(valor, color = VerdeOscuro, fontWeight = FontWeight.Bold)
    }
}
