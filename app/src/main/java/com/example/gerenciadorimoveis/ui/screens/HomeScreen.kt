package com.example.gerenciadorimoveis.ui.screens

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.HomeWork
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gerenciadorimoveis.domain.model.Imovel
import com.example.gerenciadorimoveis.ui.viewmodel.ImovelViewModel
import java.text.NumberFormat
import java.util.Locale
import androidx.compose.foundation.isSystemInDarkTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: ImovelViewModel,
    onNavigateToCadastro: () -> Unit
) {
    // Coleta a lista do banco de dados em tempo real (Flow -> State)
    val listaImoveis by viewModel.listaImoveis.collectAsState(initial = emptyList())

    // Estado para controlar qual imóvel está sendo editado no Popup modal
    var imovelParaEditar by remember { mutableStateOf<Imovel?>(null) }

    // Cores dinâmicas que se adaptam sozinhas ao Modo Claro / Escuro
    val primaryGreen = MaterialTheme.colorScheme.primary
    val textDark = MaterialTheme.colorScheme.onBackground
    val bgLight = MaterialTheme.colorScheme.background
    val topBarSurface = MaterialTheme.colorScheme.surface

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Meus Imóveis",
                        fontWeight = FontWeight.Bold,
                        color = textDark,
                        fontSize = 22.sp
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = topBarSurface)
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onNavigateToCadastro,
                containerColor = primaryGreen,
                contentColor = Color.White,
                shape = RoundedCornerShape(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Cadastrar Novo Imóvel"
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(bgLight)
                .padding(paddingValues)
        ) {
            if (listaImoveis.isEmpty()) {
                //vazio enquanto Nenhum imóvel cadastrad
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(32.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.HomeWork,
                            contentDescription = null,
                            modifier = Modifier.size(64.dp),
                            tint = Color(0xFF94A3B8)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "Nenhum imóvel cadastrado",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = textDark
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Toque no botão + abaixo para cadastrar o seu primeiro imóvel no sistema.",
                            fontSize = 14.sp,
                            color = if (isSystemInDarkTheme()) Color(0xFF94A3B8) else Color(0xFF64748B),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            } else {
                // lista de imoveiss
                LazyColumn(
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(listaImoveis) { imovel ->
                        ImovelCard(
                            imovel = imovel,
                            onEdit = { imovelParaEditar = imovel },
                            onDelete = { viewModel.deletarImovel(imovel) }
                        )
                    }
                }
            }
        }

        // popupmodal de edição
        imovelParaEditar?.let { imovel ->
            EditarImovelDialog(
                imovel = imovel,
                onDismiss = { imovelParaEditar = null },
                onSave = { imovelAtualizado ->
                    viewModel.atualizarImovel(imovelAtualizado)
                    imovelParaEditar = null
                }
            )
        }
    }
}

@Composable
fun ImovelCard(
    imovel: Imovel,
    onEdit: () -> Unit,
    onDelete: () -> Unit
) {
    val context = LocalContext.current

    // Formatador de moeda para R$
    val formatadorMoeda = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
    val precoFormatado = formatadorMoeda.format(imovel.preco)

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        //  Troca o branco fixo pela cor de superfície do tema atual
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Linha superior: Badge do Tipo, Preço e Botões de Ação
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
                    shape = RoundedCornerShape(6.dp)
                ) {
                    Text(
                        text = imovel.tipo.uppercase(),
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = precoFormatado,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.ExtraBold,
                        //  Letra preta no modo claro, branca no modo escuro
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.width(4.dp))

                    // Botão GPS / Ver no Mapa
                    IconButton(
                        onClick = {
                            if (imovel.logradouro.isNotBlank()) {
                                val gmmIntentUri = Uri.parse("geo:0,0?q=${Uri.encode(imovel.logradouro)}")
                                val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                                try {
                                    context.startActivity(mapIntent)
                                } catch (e: Exception) {
                                    Toast.makeText(context, "Nenhum aplicativo de mapas encontrado.", Toast.LENGTH_SHORT).show()
                                }
                            } else {
                                Toast.makeText(context, "Imóvel sem endereço cadastrado.", Toast.LENGTH_SHORT).show()
                            }
                        },
                        modifier = Modifier.size(32.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Place,
                            contentDescription = "Ver no Mapa",
                            tint = Color(0xFFEA580C),
                            modifier = Modifier.size(20.dp)
                        )
                    }

                    // Botão Editar
                    IconButton(onClick = onEdit, modifier = Modifier.size(32.dp)) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "Editar",
                            tint = Color(0xFF3B82F6),
                            modifier = Modifier.size(18.dp)
                        )
                    }

                    // Botão Excluir
                    IconButton(onClick = onDelete, modifier = Modifier.size(32.dp)) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Excluir",
                            tint = Color(0xFFEF4444),
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Título do Imóvel
            Text(
                text = imovel.titulo,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                // Cor adaptável para o título
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(4.dp))

            // Endereço / Logradouro
            if (imovel.logradouro.isNotBlank()) {
                Text(
                    text = imovel.logradouro,
                    fontSize = 13.sp,
                    color = if (isSystemInDarkTheme()) Color(0xFF94A3B8) else Color(0xFF64748B),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            // Descrição (com Área integrada)
            if (imovel.descricao.isNotBlank()) {
                Spacer(modifier = Modifier.height(8.dp))
                HorizontalDivider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.08f))
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = imovel.descricao,
                    fontSize = 13.sp,
                    color = if (isSystemInDarkTheme()) Color(0xFFCBD5E1) else Color(0xFF475569),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

// componente popup para edição rápida
@Composable
fun EditarImovelDialog(
    imovel: Imovel,
    onDismiss: () -> Unit,
    onSave: (Imovel) -> Unit
) {
    var titulo by remember { mutableStateOf(imovel.titulo) }
    var precoTexto by remember { mutableStateOf(imovel.preco.toString()) }
    var endereco by remember { mutableStateOf(imovel.logradouro) }
    var descricao by remember { mutableStateOf(imovel.descricao) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(text = "Editar Imóvel", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)
        },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(
                    value = titulo,
                    onValueChange = { titulo = it },
                    label = { Text("Título") },
                    singleLine = true
                )
                OutlinedTextField(
                    value = precoTexto,
                    onValueChange = { precoTexto = it },
                    label = { Text("Valor (R$)") },
                    singleLine = true
                )
                OutlinedTextField(
                    value = endereco,
                    onValueChange = { endereco = it },
                    label = { Text("Endereço") },
                    singleLine = true
                )
                OutlinedTextField(
                    value = descricao,
                    onValueChange = { descricao = it },
                    label = { Text("Descrição") },
                    maxLines = 3
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    val precoFormatado = precoTexto
                        .replace("R$", "").replace(".", "").replace(",", ".").trim()
                        .toDoubleOrNull() ?: imovel.preco

                    val imovelAtualizado = imovel.copy(
                        titulo = titulo,
                        preco = precoFormatado,
                        logradouro = endereco,
                        descricao = descricao
                    )
                    onSave(imovelAtualizado)
                },
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                Text("Salvar", color = Color.White)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancelar", color = if (isSystemInDarkTheme()) Color(0xFF94A3B8) else Color(0xFF64748B))
            }
        }
    )
}