package com.example.gerenciadorimoveis.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gerenciadorimoveis.data.local.ImovelDatabase
import com.example.gerenciadorimoveis.ui.screens.CadastroScreen
import com.example.gerenciadorimoveis.ui.screens.HomeScreen
import com.example.gerenciadorimoveis.ui.screens.LoginScreen
import com.example.gerenciadorimoveis.ui.viewmodel.ImovelViewModel

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
            val context = LocalContext.current
            val banco = ImovelDatabase.getDatabase(context.applicationContext)
            val dao = banco.imovelDao()

            val imovelViewModel: ImovelViewModel = viewModel(
                factory = object : ViewModelProvider.Factory {
                    override fun <T : ViewModel> create(modelClass: Class<T>): T {
                        return ImovelViewModel(dao) as T
                    }
                }
            )

            HomeScreen(
                viewModel = imovelViewModel,
                onNavigateToCadastro = {
                    navController.navigate("cadastro")
                }
            )
        }

        // 3a rota -> Tela de Cadastro
        composable("cadastro") {
            // acesso para o room
            val context = LocalContext.current
            val banco = ImovelDatabase.getDatabase(context)
            val dao = banco.imovelDao()

            // viewmodel + DAO, conecção
            val imovelViewModel: ImovelViewModel = viewModel(
                factory = object : ViewModelProvider.Factory {
                    override fun <T : ViewModel> create(modelClass: Class<T>): T {
                        return ImovelViewModel(dao) as T
                    }
                }
            )

            // direcionando viewmodel para tela de cadastro para salvar os dados
            CadastroScreen(
                viewModel = imovelViewModel,
                onNavigateBack = {
                    navController.popBackStack() // Fecha o cadastro e volta pra Home
                }
            )
        }
    }
}