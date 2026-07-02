package com.example.gerenciadorimoveis.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable

/*fun LoginScreen() {
    val darkBackground = Color(0xFF0D1B2A)
    val primaryGreen = Color(0xFF22C55E)
    val textColor = Color.White

    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }*/

                // atualizando para usar navigation
fun LoginScreen(onNavigateToHome: () -> Unit = {}) { // <-- ALTERAÇÃO AQUI
    val darkBackground = Color(0xFF0D1B2A)
    val primaryGreen = Color(0xFF22C55E)
    val textColor = Color.White

    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(darkBackground)
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "GestorLar",
            color = primaryGreen,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Bem-vindo de volta!",
            color = textColor,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold
        )

        Text(
            text = "Faça login para continuar",
            color = Color.Gray,
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Campos de E-mail e Senha, em sequencia
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("E-mail", color = Color.Gray) },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = primaryGreen,
                unfocusedBorderColor = Color.Gray,
                focusedTextColor = textColor,
                unfocusedTextColor = textColor,
                cursorColor = primaryGreen
            ),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = senha,
            onValueChange = { senha = it },
            label = { Text("Senha", color = Color.Gray) },
            visualTransformation = PasswordVisualTransformation(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = primaryGreen,
                unfocusedBorderColor = Color.Gray,
                focusedTextColor = textColor,
                unfocusedTextColor = textColor,
                cursorColor = primaryGreen
            ),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = { onNavigateToHome() },
                // ATIVAÇÃO NAVIGATE EM F
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = primaryGreen),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text("Entrar", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }
    }
}
// teste
@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}