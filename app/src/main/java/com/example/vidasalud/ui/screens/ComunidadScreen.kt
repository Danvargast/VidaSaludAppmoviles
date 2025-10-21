@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.vidasalud.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.vidasalud.ui.theme.*


@Composable
fun ComunidadScreen(navController: NavController) {
    var nuevoPost by remember { mutableStateOf("") }
    var posts by remember {
        mutableStateOf(
            listOf(
                Post("María", "Hoy logré correr 5 km 💪", 15),
                Post("Carlos", "Desayuné frutas y avena 🍓🥣", 9),
                Post("Sofía", "Día 10 sin azúcar añadida 🙌", 22),
                Post("Ana", "¡Empecé mi rutina de yoga matinal! 🧘‍♀️", 18)
            )
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Comunidad VidaSalud", color = Blanco) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Volver", tint = Blanco)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = VerdeMenta)
            )
        },
        bottomBar = {
            // 🔹 Área para escribir un nuevo post
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Blanco)
                    .padding(horizontal = 12.dp, vertical = 10.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    OutlinedTextField(
                        value = nuevoPost,
                        onValueChange = { nuevoPost = it },
                        placeholder = { Text("Comparte algo con la comunidad...") },
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(25.dp)),
                        shape = RoundedCornerShape(25.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = GrisClaro,
                            unfocusedContainerColor = GrisClaro,
                            cursorColor = VerdeMenta
                        )
                    )
                    Spacer(Modifier.width(8.dp))
                    IconButton(
                        onClick = {
                            if (nuevoPost.isNotBlank()) {
                                posts = posts + Post("Tú", nuevoPost.trim(), 0)
                                nuevoPost = ""
                            }
                        },
                        modifier = Modifier
                            .size(48.dp)
                            .background(VerdeMenta, CircleShape)
                    ) {
                        Icon(Icons.Filled.Send, contentDescription = "Publicar", tint = Blanco)
                    }
                }
            }
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
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 18.dp, vertical = 22.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                item {
                    Text(
                        "Comparte tus logros 💬",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = VerdeOscuro
                    )
                    Spacer(Modifier.height(6.dp))
                    Text(
                        "Motiva a otros compartiendo tus avances, hábitos o reflexiones del día.",
                        color = GrisTexto,
                        fontSize = 14.sp
                    )
                    Spacer(Modifier.height(10.dp))
                }

                items(posts.size) { index ->
                    PostCardPremium(posts[index])
                }
            }
        }
    }
}

data class Post(
    val autor: String,
    val contenido: String,
    var likes: Int
)

@Composable
fun PostCardPremium(post: com.example.vidasalud.ui.screens.Post) {
    var likes by remember { mutableStateOf(post.likes) }
    var liked by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 120.dp)
            .shadow(8.dp, RoundedCornerShape(22.dp)),
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(containerColor = Blanco)
    ) {
        Column(
            modifier = Modifier
                .padding(18.dp)
                .fillMaxWidth()
        ) {
            // 🔹 Encabezado
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(45.dp)
                        .background(
                            Brush.linearGradient(listOf(VerdeMenta, Color(0xFF9CE3CD))),
                            shape = CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        post.autor.first().toString(),
                        color = Blanco,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                }
                Column {
                    Text(post.autor, fontWeight = FontWeight.Bold, color = VerdeOscuro)
                    Text("hace un momento", fontSize = 12.sp, color = GrisTexto.copy(alpha = 0.7f))
                }
            }

            Spacer(Modifier.height(10.dp))

            // 🔹 Contenido
            Text(
                post.contenido,
                color = GrisTexto,
                fontSize = 15.sp,
                lineHeight = 20.sp,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(Modifier.height(14.dp))

            // 🔹 Reacciones
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                IconButton(onClick = {
                    liked = !liked
                    likes += if (liked) 1 else -1
                }) {
                    Icon(
                        Icons.Filled.Favorite,
                        contentDescription = "Me gusta",
                        tint = if (liked) Color(0xFFE57373) else VerdeMenta,
                        modifier = Modifier.size(24.dp)
                    )
                }

                AnimatedVisibility(visible = likes > 0) {
                    Text(
                        "$likes me gusta${if (likes == 1) "" else "s"}",
                        color = VerdeOscuro,
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}
