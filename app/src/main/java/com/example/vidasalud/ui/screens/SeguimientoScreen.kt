@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.vidasalud.ui.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DirectionsWalk
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.NightlightRound
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.vidasalud.ui.theme.Blanco
import com.example.vidasalud.ui.theme.GrisClaro
import com.example.vidasalud.ui.theme.GrisTexto
import com.example.vidasalud.ui.theme.VerdeMenta
import com.example.vidasalud.ui.theme.VerdeOscuro

@Composable
fun SeguimientoScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Seguimiento de Salud", color = Blanco) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Volver", tint = Blanco)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = VerdeMenta)
            )
        },
        containerColor = GrisClaro
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color(0xFFE8FFF7), Color(0xFFFFFFFF))
                    )
                )
                .padding(padding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp, vertical = 25.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "Resumen de tu bienestar 🌿",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = VerdeOscuro
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    "Tu progreso diario de salud y energía",
                    fontSize = 14.sp,
                    color = GrisTexto
                )
                Spacer(Modifier.height(28.dp))

                // 🔹 Primera fila
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    TarjetaProgresoPremium(
                        titulo = "Pasos",
                        valor = 8300,
                        meta = 10000,
                        color1 = VerdeMenta,
                        color2 = Color(0xFF7AD9BC),
                        icono = Icons.Filled.DirectionsWalk
                    )
                    TarjetaProgresoPremium(
                        titulo = "Calorías",
                        valor = 1150,
                        meta = 1500,
                        color1 = Color(0xFFFFB347),
                        color2 = Color(0xFFFFA726),
                        icono = Icons.Filled.LocalFireDepartment
                    )
                }

                Spacer(Modifier.height(20.dp))

                // 🔹 Segunda fila
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    TarjetaProgresoPremium(
                        titulo = "Sueño",
                        valor = 7,
                        meta = 8,
                        color1 = Color(0xFF7BBEF2),
                        color2 = Color(0xFF4A9FE6),
                        icono = Icons.Filled.NightlightRound
                    )
                    TarjetaProgresoPremium(
                        titulo = "Ritmo cardíaco",
                        valor = 76,
                        meta = 100,
                        color1 = Color(0xFFE57373),
                        color2 = Color(0xFFEF5350),
                        icono = Icons.Filled.Favorite
                    )
                }

                Spacer(Modifier.height(30.dp))

                // 💬 Consejo motivacional
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(6.dp, RoundedCornerShape(20.dp)),
                    colors = CardDefaults.cardColors(containerColor = Blanco),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Column(
                        Modifier.padding(18.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            "🌞 Consejo del día",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = VerdeOscuro
                        )
                        Spacer(Modifier.height(8.dp))
                        Text(
                            "💧 Toma pequeños descansos entre tus actividades y recuerda hidratarte cada hora.",
                            fontSize = 14.sp,
                            color = GrisTexto,
                            lineHeight = 18.sp
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun TarjetaProgresoPremium(
    titulo: String,
    valor: Int,
    meta: Int,
    color1: Color,
    color2: Color,
    icono: androidx.compose.ui.graphics.vector.ImageVector
) {
    val progreso = (valor.toFloat() / meta.toFloat()).coerceIn(0f, 1f)
    val animatedProgress by animateFloatAsState(targetValue = progreso, label = "progressAnim")

    Card(
        modifier = Modifier
            .width(160.dp)
            .height(180.dp)
            .shadow(10.dp, RoundedCornerShape(20.dp)),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Blanco)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(contentAlignment = Alignment.Center) {
                Canvas(modifier = Modifier.size(90.dp)) {
                    // Fondo
                    drawArc(
                        color = GrisClaro,
                        startAngle = -90f,
                        sweepAngle = 360f,
                        useCenter = false,
                        style = Stroke(width = 10f, cap = StrokeCap.Round)
                    )
                    // Progreso animado
                    drawArc(
                        brush = Brush.linearGradient(listOf(color1, color2)),
                        startAngle = -90f,
                        sweepAngle = 360 * animatedProgress,
                        useCenter = false,
                        style = Stroke(width = 10f, cap = StrokeCap.Round)
                    )
                }
                Box(
                    modifier = Modifier
                        .size(42.dp)
                        .background(VerdeMenta.copy(alpha = 0.1f), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(icono, contentDescription = titulo, tint = color1, modifier = Modifier.size(26.dp))
                }
            }
            Spacer(Modifier.height(10.dp))
            Text(
                "$valor / $meta",
                fontWeight = FontWeight.Bold,
                color = VerdeOscuro,
                fontSize = 15.sp
            )//hola
            Text(
                titulo,
                color = GrisTexto,
                fontSize = 13.sp
            )
        }
    }
}
