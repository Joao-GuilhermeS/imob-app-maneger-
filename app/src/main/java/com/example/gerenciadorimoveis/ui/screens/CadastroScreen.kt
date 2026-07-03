package com.example.gerenciadorimoveis.ui.screens

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Apartment
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Landscape
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
    val context = LocalContext.current

    // padrões de cores
    val primaryGreen = MaterialTheme.colorScheme.primary
    val textDark = MaterialTheme.colorScheme.onBackground
    val bgLight = MaterialTheme.colorScheme.background
    val surfaceColor = MaterialTheme.colorScheme.surface
    val borderVisible = if (isSystemInDarkTheme()) Color(0xFF64748B) else Color(0xFF94A3B8)

    // declaração e estados dos campos
    var titulo by remember { mutableStateOf("") }
    var tipoSelecionado by remember { mutableStateOf("Casa") }
    var valor by remember { mutableStateOf("") }
    var area by remember { mutableStateOf("") }
    var cep by remember { mutableStateOf("") }
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
                colors = TopAppBarDefaults.topAppBarColors(containerColor = surfaceColor)
            )
        },
        bottomBar = {
            Surface(
                shadowElevation = 8.dp,
                color = surfaceColor,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = {
                        if (titulo.isNotBlank() && valor.isNotBlank()) {
                            viewModel.salvarImovel(
                                titulo = titulo,
                                tipo = tipoSelecionado,
                                valorTexto = valor,
                                areaTexto = area,
                                enderecoGeral = endereco,
                                descricao = descricao,
                                onSucesso = {
                                    onNavigateBack()
                                }
                            )
                        } else {
                            Toast.makeText(context, "Preencha pelo menos o Título e o Valor!", Toast.LENGTH_SHORT).show()
                        }
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

            // Título
            Text("Título do imóvel", fontWeight = FontWeight.SemiBold, fontSize = 14.sp, color = textDark)
            Spacer(modifier = Modifier.height(4.dp))
            OutlinedTextFieldAltoContraste(
                value = titulo,
                onValueChange = { titulo = it },
                placeholder = "Ex.: Casa moderna no Centro"
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Tipo de imóvel
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
                            containerColor = if (selecionado) primaryGreen.copy(alpha = 0.1f) else surfaceColor
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

            // Valor e Área
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

            // implementação de busca automatica por cep (consumo de api)
            Text("CEP (Preenchimento automático)", fontWeight = FontWeight.SemiBold, fontSize = 14.sp, color = textDark)
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(modifier = Modifier.weight(1f)) {
                    OutlinedTextFieldAltoContraste(
                        value = cep,
                        onValueChange = { if (it.length <= 9) cep = it },
                        placeholder = "Ex.: 63900-000"
                    )
                }
                Button(
                    onClick = {
                        Toast.makeText(context, "Consultando CEP...", Toast.LENGTH_SHORT).show()
                        viewModel.buscarEnderecoPorCep(
                            cep = cep,
                            onSucesso = { enderecoEncontrado ->
                                endereco = enderecoEncontrado // Preenche o endereço sozinho!
                            },
                            onErro = {
                                Toast.makeText(context, "CEP não encontrado. Digite manualmente.", Toast.LENGTH_SHORT).show()
                            }
                        )
                    },
                    modifier = Modifier.height(54.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3B82F6)) // Azul
                ) {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "Buscar CEP", tint = Color.White)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Buscar", color = Color.White, fontWeight = FontWeight.Bold)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Endereço / Bairro
            Text("Endereço / Bairro", fontWeight = FontWeight.SemiBold, fontSize = 14.sp, color = textDark)
            Spacer(modifier = Modifier.height(4.dp))
            OutlinedTextFieldAltoContraste(
                value = endereco,
                onValueChange = { endereco = it },
                placeholder = "Ex.: Rua Plácido Castelo, Centro"
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Descrição
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

// Garantir legibilidade e suporte automático ao Modo Escuro / Claro
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
                color = if (isSystemInDarkTheme()) Color(0xFF94A3B8) else Color(0xFF64748B)
            )
        },
        modifier = Modifier.fillMaxWidth(),
        minLines = minLines,
        maxLines = maxLines,
        shape = RoundedCornerShape(8.dp),
        colors = OutlinedTextFieldDefaults.colors(
            // Borda verde quando o campo é clicado
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = if (isSystemInDarkTheme()) Color(0xFF64748B) else Color(0xFF94A3B8),

            // Letra preta no claro e branca no escuro
            focusedTextColor = MaterialTheme.colorScheme.onSurface,
            unfocusedTextColor = MaterialTheme.colorScheme.onSurface,

            //Fundo branco no claro e chumbo-escuro no modo escuro, modo claro/escuro
            focusedContainerColor = MaterialTheme.colorScheme.surface,
            unfocusedContainerColor = MaterialTheme.colorScheme.surface
        )
    )
}