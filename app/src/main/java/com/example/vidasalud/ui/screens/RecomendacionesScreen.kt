package com.example.vidasalud.ui.screens



import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.vidasalud.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecomendacionesScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Recomendaciones Personalizadas", color = Blanco) },
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
                        listOf(Color(0xFFE8FFF7), Color(0xFFFFFFFF))
                    )
                )
                .padding(padding)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp, vertical = 24.dp),
                verticalArrangement = Arrangement.spacedBy(18.dp)
            ) {
                item {
                    Text(
                        "Basadas en tu bienestar actual 💚",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = VerdeOscuro
                    )
                    Spacer(Modifier.height(6.dp))
                    Text(
                        "Estas sugerencias están pensadas para mejorar tu día y cuidar tu cuerpo.",
                        color = GrisTexto,
                        fontSize = 14.sp
                    )
                }

                item {
                    RecomendacionCard(
                        titulo = "💧 Hidratación",
                        descripcion = "Bebe al menos 2 litros de agua a lo largo del día. Lleva una botella contigo para mantenerte hidratado.",
                        colorInicio = Color(0xFF9DE3CF),
                        colorFin = VerdeMenta,
                        icono = Icons.Filled.WaterDrop
                    )
                }

                item {
                    RecomendacionCard(
                        titulo = "🏃 Actividad física",
                        descripcion = "Haz una caminata de 20 minutos o realiza estiramientos suaves para activar tu circulación.",
                        colorInicio = Color(0xFFFFD280),
                        colorFin = Color(0xFFFFB347),
                        icono = Icons.Filled.FitnessCenter
                    )
                }

                item {
                    RecomendacionCard(
                        titulo = "🍎 Alimentación saludable",
                        descripcion = "Incluye frutas, verduras y alimentos naturales. Evita comidas ultraprocesadas y azúcares refinados.",
                        colorInicio = Color(0xFFFFE1A6),
                        colorFin = Color(0xFFFBD27A),
                        icono = Icons.Filled.Restaurant
                    )
                }

                item {
                    RecomendacionCard(
                        titulo = "🧘 Relajación mental",
                        descripcion = "Tómate 10 minutos para respirar profundamente o meditar. Cierra los ojos y enfócate en el presente.",
                        colorInicio = Color(0xFFC6B5F8),
                        colorFin = Color(0xFF9D7BEA),
                        icono = Icons.Filled.SelfImprovement
                    )
                }

                item {
                    RecomendacionCard(
                        titulo = "🌙 Descanso reparador",
                        descripcion = "Procura dormir entre 7 y 8 horas. Evita pantallas brillantes 30 minutos antes de dormir.",
                        colorInicio = Color(0xFFA1D7F8),
                        colorFin = Color(0xFF7BBEF2),
                        icono = Icons.Filled.NightlightRound
                    )
                }
            }
        }
    }
}

@Composable
fun RecomendacionCard(
    titulo: String,
    descripcion: String,
    colorInicio: Color,
    colorFin: Color,
    icono: androidx.compose.ui.graphics.vector.ImageVector
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 120.dp)
            .padding(bottom = 4.dp),
        shape = RoundedCornerShape(18.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        Box(
            modifier = Modifier
                .background(
                    brush = Brush.horizontalGradient(listOf(colorInicio, colorFin)),
                    shape = RoundedCornerShape(18.dp)
                )
                .fillMaxSize()
                .padding(horizontal = 20.dp, vertical = 16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                // Ícono de recomendación
                Icon(
                    icono,
                    contentDescription = titulo,
                    tint = Color.White.copy(alpha = 0.95f),
                    modifier = Modifier.size(40.dp)
                )

                // Texto de recomendación
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        titulo,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Spacer(Modifier.height(6.dp))
                    Text(
                        descripcion,
                        fontSize = 13.5.sp,
                        lineHeight = 18.sp,
                        color = Color.White.copy(alpha = 0.95f)
                    )
                }
            }
        }
    }
}
