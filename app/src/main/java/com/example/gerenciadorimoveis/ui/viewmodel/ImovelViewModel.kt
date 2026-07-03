package com.example.gerenciadorimoveis.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gerenciadorimoveis.data.local.ImovelDao
import com.example.gerenciadorimoveis.domain.model.Imovel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class ImovelViewModel(private val dao: ImovelDao) : ViewModel() {

    // Lista reativa que sera chamada na hommeScreen
    val listaImoveis: Flow<List<Imovel>> = dao.getTodosImoveis()

    // Função ponte entre a tela cadadstro e o banco estruturado
    fun salvarImovel(
        titulo: String,
        tipo: String,
        valorTexto: String,
        areaTexto: String,
        enderecoGeral: String,
        descricao: String,
        onSucesso: () -> Unit
    ) {
        viewModelScope.launch {
            // Limpa e converte o valor digitado (ex: "350.000,00") para Double com segurança
            val precoFormatado = valorTexto
                .replace("R$", "")
                .replace(".", "")
                .replace(",", ".")
                .trim()
                .toDoubleOrNull() ?: 0.0

            val descricaoCompleta = if (areaTexto.isNotBlank()) {
                "Área: ${areaTexto}m² | $descricao"
            } else {
                descricao
            }

            // montagem da entidade
            val novoImovel = Imovel(
                titulo = titulo,
                tipo = tipo,
                preco = precoFormatado,
                descricao = descricaoCompleta,
                logradouro = enderecoGeral, // Recebe o endereço digitado na tela
                cep = "Não informado",
                bairro = "Geral",
                cidade = "Quixadá",
                estado = "CE",
                numero = "S/N"
            )

            // salvar em segundo planoo
            dao.salvarImovel(novoImovel)
            onSucesso() // direciona para a Home
        }
    }
}