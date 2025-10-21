

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.vidasalud.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConfiguracionScreen(navController: NavController) {
    // Estados de los interruptores
    var notificaciones by remember { mutableStateOf(true) }
    var modoOscuro by remember { mutableStateOf(false) }
    var privacidad by remember { mutableStateOf(true) }
    var idiomaSeleccionado by remember { mutableStateOf("Español") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Configuración y Ajustes", color = Blanco) },
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
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(GrisClaro)
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                "Preferencias generales",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = VerdeOscuro
            )

            // === INTERRUPTOR DE NOTIFICACIONES ===
            AjusteSwitch(
                titulo = "Notificaciones",
                descripcion = "Recibir recordatorios y avisos diarios",
                checked = notificaciones,
                onCheckedChange = { notificaciones = it }
            )

            // === INTERRUPTOR DE MODO OSCURO ===
            AjusteSwitch(
                titulo = "Modo oscuro",
                descripcion = "Cambiar el tema de la aplicación",
                checked = modoOscuro,
                onCheckedChange = { modoOscuro = it }
            )

            // === INTERRUPTOR DE PRIVACIDAD ===
            AjusteSwitch(
                titulo = "Privacidad",
                descripcion = "Permitir compartir tus datos de salud",
                checked = privacidad,
                onCheckedChange = { privacidad = it }
            )

            // === SECCIÓN DE IDIOMA ===
            Spacer(Modifier.height(10.dp))
            Text(
                "Idioma de la aplicación",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = VerdeOscuro
            )

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Blanco),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(Modifier.padding(16.dp)) {
                    IdiomaOpcion("Español", idiomaSeleccionado) { idiomaSeleccionado = "Español" }
                    IdiomaOpcion("Inglés", idiomaSeleccionado) { idiomaSeleccionado = "Inglés" }
                    IdiomaOpcion("Portugués", idiomaSeleccionado) { idiomaSeleccionado = "Portugués" }
                }
            }

            Spacer(Modifier.height(20.dp))

            // === BOTÓN GUARDAR CAMBIOS ===
            Button(
                onClick = {
                    // Aquí podrías guardar en DataStore o mostrar un mensaje
                    navController.popBackStack()
                },
                colors = ButtonDefaults.buttonColors(containerColor = VerdeMenta),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(25.dp)
            ) {
                Text("Guardar cambios", fontSize = 17.sp, fontWeight = FontWeight.Bold, color = Blanco)
            }
        }
    }
}

// 🔘 COMPONENTE PERSONALIZADO DE SWITCH
@Composable
fun AjusteSwitch(
    titulo: String,
    descripcion: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Blanco),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(Modifier.weight(1f)) {
                Text(titulo, fontWeight = FontWeight.Bold, color = VerdeOscuro)
                Text(descripcion, color = GrisTexto, fontSize = 13.sp)
            }
            Switch(
                checked = checked,
                onCheckedChange = onCheckedChange,
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Blanco,
                    checkedTrackColor = VerdeMenta
                )
            )
        }
    }
}

// 🌐 COMPONENTE PARA SELECCIÓN DE IDIOMA
@Composable
fun IdiomaOpcion(
    idioma: String,
    seleccionado: String,
    onSeleccionado: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(idioma, color = VerdeOscuro)
        RadioButton(
            selected = idioma == seleccionado,
            onClick = onSeleccionado,
            colors = RadioButtonDefaults.colors(selectedColor = VerdeMenta)
        )
    }
}


