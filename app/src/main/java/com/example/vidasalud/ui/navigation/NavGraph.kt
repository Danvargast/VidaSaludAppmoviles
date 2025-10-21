package com.example.vidasalud.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.vidasalud.ui.screens.*
import com.example.vidasalud.viewmodel.UsuarioViewModel
import androidx.lifecycle.viewmodel.compose.viewModel



@Composable
fun NavGraph() {
    val navController = rememberNavController()

    val usuarioViewModel: UsuarioViewModel = viewModel()


    NavHost(navController = navController, startDestination = "login") {
        composable("login") { LoginScreen(navController, usuarioViewModel) }
        composable("registro") { RegistroScreen(navController,usuarioViewModel) }
        composable("principal") { PrincipalScreen(navController) } // luego la crearemos
        composable("recomendaciones") { RecomendacionesScreen(navController) }
        composable("seguimiento") { SeguimientoScreen(navController) }
        composable("comunidad") { ComunidadScreen(navController) }
        composable("perfil") { PerfilScreen(navController, usuarioViewModel) }
        composable("configuracion") { ConfiguracionScreen(navController) }


    }
}
