package com.example.gerenciadorimoveis.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gerenciadorimoveis.ui.screens.HomeScreen
import com.example.gerenciadorimoveis.ui.screens.LoginScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    // NavHost "mapa". partindo de login p próx tela
    NavHost(navController = navController, startDestination = "login") {

        // 1a rota -> Tela de Login
        composable("login") {
            LoginScreen(
                onNavigateToHome = {
                    // Quando clicar em entrar, vai para "home"
                    navController.navigate("home") {
                        // Limpa a tela de login do histórico. Assim, se o utilizador
                        // apertar o botão "Voltar" do celular, ele sai do app em vez de
                        // voltar para a tela de login logado.
                        popUpTo("login") { inclusive = true }
                    }
                }
            )
        }

        // 2a rota -> Tela Principal
        composable("home") {
            HomeScreen()
        }
    }
}