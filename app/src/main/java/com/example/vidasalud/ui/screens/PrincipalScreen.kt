package com.example.vidasalud.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
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

@Composable
fun PrincipalScreen(navController: NavController) {
    var selectedIndex by remember { mutableStateOf(0) }

    Scaffold(
        bottomBar = {
            // 🔹 NavBar Flotante y estilizada
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                contentAlignment = Alignment.Center
            ) {
                Card(
                    shape = RoundedCornerShape(40.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 12.dp),
                    colors = CardDefaults.cardColors(containerColor = Blanco.copy(alpha = 0.95f)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(70.dp)
                ) {
                    NavigationBar(
                        containerColor = Color.Transparent,
                        tonalElevation = 0.dp,
                        modifier = Modifier.clip(RoundedCornerShape(40.dp))
                    ) {
                        val items = listOf(
                            Pair(Icons.Filled.Home, "Inicio"),
                            Pair(Icons.Filled.FitnessCenter, "Seguimiento"),
                            Pair(Icons.Filled.TipsAndUpdates, "Recomendaciones"),
                            Pair(Icons.Filled.Group, "Comunidad"),
                            Pair(Icons.Filled.Person, "Perfil")
                        )

                        items.forEachIndexed { index, item ->
                            val selected = selectedIndex == index
                            NavigationBarItem(
                                selected = selected,
                                onClick = {
                                    selectedIndex = index
                                    when (index) {
                                        0 -> navController.navigate("principal")
                                        1 -> navController.navigate("seguimiento")
                                        2 -> navController.navigate("recomendaciones")
                                        3 -> navController.navigate("comunidad")
                                        4 -> navController.navigate("perfil")
                                    }
                                },
                                icon = {
                                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                        Icon(
                                            item.first,
                                            contentDescription = item.second,
                                            tint = if (selected) VerdeMenta else VerdeOscuro.copy(alpha = 0.5f),
                                            modifier = Modifier.size(if (selected) 30.dp else 26.dp)
                                        )
                                        Spacer(Modifier.height(3.dp))
                                        Text(
                                            text = item.second,
                                            fontSize = 12.sp,
                                            color = if (selected) VerdeMenta else VerdeOscuro.copy(alpha = 0.6f)
                                        )
                                    }
                                },
                                colors = NavigationBarItemDefaults.colors(
                                    indicatorColor = Color.Transparent
                                )
                            )
                        }
                    }
                }
            }
        },
        containerColor = GrisClaro
    ) { padding ->
        // Fondo con degradado sutil
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        listOf(Color(0xFFE8FFF7), Color(0xFFF7F7F7))
                    )
                )
        ) {
            Column(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
                    .padding(horizontal = 20.dp, vertical = 30.dp)
            ) {
                Text(
                    "¡Hola, bienvenido a VidaSalud!",
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold,
                    color = VerdeOscuro
                )

                Spacer(Modifier.height(24.dp))

                // Tarjetas de resumen de salud
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    TarjetaResumen("Pasos", "5.432", "Hoy", Icons.Default.DirectionsWalk)
                    TarjetaResumen("Calorías", "1.250 kcal", "Diarias", Icons.Default.LocalFireDepartment)
                }

                Spacer(Modifier.height(16.dp))

                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    TarjetaResumen("Sueño", "7 h", "Última noche", Icons.Default.NightlightRound)
                    TarjetaResumen("Ritmo cardíaco", "78 bpm", "Actual", Icons.Default.Favorite)
                }

                Spacer(Modifier.height(24.dp))

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Blanco),
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(8.dp)
                ) {
                    Column(Modifier.padding(16.dp)) {
                        Text(
                            "Recomendación del día",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = VerdeOscuro
                        )
                        Spacer(Modifier.height(8.dp))
                        Text(
                            "💧 ¡Mantente hidratado! Bebe al menos 2 litros de agua hoy para mantener tu energía y concentración.",
                            color = GrisTexto
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun TarjetaResumen(
    titulo: String,
    valor: String,
    subtitulo: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector
) {
    Card(
        modifier = Modifier
            .width(160.dp)
            .height(140.dp),
        colors = CardDefaults.cardColors(containerColor = Blanco),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                icon,
                contentDescription = titulo,
                tint = VerdeMenta,
                modifier = Modifier.size(40.dp)
            )
            Spacer(Modifier.height(8.dp))
            Text(valor, fontWeight = FontWeight.Bold, color = VerdeOscuro)
            Text(titulo, color = VerdeOscuro)
            Text(subtitulo, color = GrisTexto, fontSize = 12.sp)
        }
    }
}
