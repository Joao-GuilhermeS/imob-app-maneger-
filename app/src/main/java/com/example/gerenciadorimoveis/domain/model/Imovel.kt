package com.example.gerenciadorimoveis.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "imoveis")
data class Imovel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val titulo: String,
    val descricao: String,
    val tipo: String,
    val preco: Double,
    val cep: String,
    val logradouro: String,
    val bairro: String,
    val cidade: String,
    val estado: String,
    val numero: String,
    val latitude: Double? = null,
    val longitude: Double? = null
)