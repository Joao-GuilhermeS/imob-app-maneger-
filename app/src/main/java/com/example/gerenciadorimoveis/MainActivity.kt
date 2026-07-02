package com.example.gerenciadorimoveis // Confirme se o pacote está correto

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.gerenciadorimoveis.ui.navigation.AppNavigation
import com.example.gerenciadorimoveis.ui.theme.GerenciadorImoveisTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // barra de status transparente
        enableEdgeToEdge()

        setContent {
            GerenciadorImoveisTheme {
                // chamada da nevegacao
                AppNavigation()
            }
        }
    }
}