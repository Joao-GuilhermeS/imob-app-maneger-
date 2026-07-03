package com.example.gerenciadorimoveis.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Apartment
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Landscape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gerenciadorimoveis.ui.viewmodel.ImovelViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CadastroScreen(
    viewModel: ImovelViewModel,
    onNavigateBack: () -> Unit = {}
) {
    // --- PALETA DE CORES COM ALTO CONTRASTE ---
    val primaryGreen = Color(0xFF16A34A)
    val textDark = Color(0xFF0F172A)
    val borderVisible = Color(0xFF64748B)
    val bgLight = Color(0xFFF8FAFC)

    // --- ESTADOS DOS CAMPOS (Tudo em uma tela só) ---
    var titulo by remember { mutableStateOf("") }
    var tipoSelecionado by remember { mutableStateOf("Casa") }
    var valor by remember { mutableStateOf("") }
    var area by remember { mutableStateOf("") }
    var endereco by remember { mutableStateOf("") }
    var descricao by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Cadastrar Imóvel",
                        fontWeight = FontWeight.Bold,
                        color = textDark,
                        fontSize = 20.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Voltar",
                            tint = textDark
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        },
        bottomBar = {
            // Botão fixo no rodapé para salvar direto
            Surface(
                shadowElevation = 8.dp,
                color = Color.White,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = {
                        onNavigateBack()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .height(54.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = primaryGreen),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = "Salvar Imóvel",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(bgLight)
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            //titulo
            Text("Título do imóvel", fontWeight = FontWeight.SemiBold, fontSize = 14.sp, color = textDark)
            Spacer(modifier = Modifier.height(4.dp))
            OutlinedTextFieldAltoContraste(
                value = titulo,
                onValueChange = { titulo = it },
                placeholder = "Ex.: Casa moderna no Centro"
            )

            Spacer(modifier = Modifier.height(16.dp))

            // tipo de alocação, com caixa de seleção hehe
            Text("Tipo de imóvel", fontWeight = FontWeight.SemiBold, fontSize = 14.sp, color = textDark)
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                val tipos = listOf("Casa" to Icons.Default.Home, "Terreno" to Icons.Default.Landscape, "Apartamento" to Icons.Default.Apartment)
                tipos.forEach { (tipo, icone) ->
                    val selecionado = tipoSelecionado == tipo
                    OutlinedButton(
                        onClick = { tipoSelecionado = tipo },
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(8.dp),
                        border = BorderStroke(
                            width = if (selecionado) 2.dp else 1.dp,
                            color = if (selecionado) primaryGreen else borderVisible
                        ),
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = if (selecionado) primaryGreen.copy(alpha = 0.1f) else Color.White
                        )
                    ) {
                        Icon(
                            imageVector = icone,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp),
                            tint = if (selecionado) primaryGreen else textDark
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = tipo,
                            color = if (selecionado) primaryGreen else textDark,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // valor e araa, um ao lado do outro
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Column(modifier = Modifier.weight(1f)) {
                    Text("Valor (R$)", fontWeight = FontWeight.SemiBold, fontSize = 14.sp, color = textDark)
                    Spacer(modifier = Modifier.height(4.dp))
                    OutlinedTextFieldAltoContraste(
                        value = valor,
                        onValueChange = { valor = it },
                        placeholder = "350.000,00"
                    )
                }
                Column(modifier = Modifier.weight(1f)) {
                    Text("Área total (m²)", fontWeight = FontWeight.SemiBold, fontSize = 14.sp, color = textDark)
                    Spacer(modifier = Modifier.height(4.dp))
                    OutlinedTextFieldAltoContraste(
                        value = area,
                        onValueChange = { area = it },
                        placeholder = "200"
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Endereço simplificado, posteriormente adcionar mains infos
            Text("Endereço / Bairro", fontWeight = FontWeight.SemiBold, fontSize = 14.sp, color = textDark)
            Spacer(modifier = Modifier.height(4.dp))
            OutlinedTextFieldAltoContraste(
                value = endereco,
                onValueChange = { endereco = it },
                placeholder = "Ex.: Rua Plácido Castelo, Centro"
            )

            Spacer(modifier = Modifier.height(16.dp))

            // descrição
            Text("Descrição / Diferenciais", fontWeight = FontWeight.SemiBold, fontSize = 14.sp, color = textDark)
            Spacer(modifier = Modifier.height(4.dp))
            OutlinedTextFieldAltoContraste(
                value = descricao,
                onValueChange = { if (it.length <= 300) descricao = it },
                placeholder = "Ex.: 3 quartos, garagem coberta, quintal amplo...",
                minLines = 3,
                maxLines = 3
            )
            Text(
                text = "${descricao.length}/300",
                fontSize = 12.sp,
                color = Color.Gray,
                modifier = Modifier.align(Alignment.End)
            )

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

  //garantir legibilidade
@Composable
fun OutlinedTextFieldAltoContraste(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    minLines: Int = 1,
    maxLines: Int = 1
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(
                text = placeholder,
                color = Color(0xFF64748B) // Cinza visível para o texto de ajuda
            )
        },
        modifier = Modifier.fillMaxWidth(),
        minLines = minLines,
        maxLines = maxLines,
        shape = RoundedCornerShape(8.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color(0xFF16A34A),
            unfocusedBorderColor = Color(0xFF64748B), // Borda nítida e visível
            focusedTextColor = Color(0xFF0F172A),
            unfocusedTextColor = Color(0xFF0F172A),
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White
        )
    )
}