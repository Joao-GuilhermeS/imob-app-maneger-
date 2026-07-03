package com.example.gerenciadorimoveis.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.gerenciadorimoveis.domain.model.Imovel
import kotlinx.coroutines.flow.Flow

@Dao
interface ImovelDao {

    // Lista todos os imóveis. O 'Flow' faz com que a lista na tela atualize qnd o banco mudar
    @Query("SELECT * FROM imoveis ORDER BY id DESC")
    fun getTodosImoveis(): Flow<List<Imovel>>

    // Busca um imóvel específico pelo ID
    @Query("SELECT * FROM imoveis WHERE id = :id")
    suspend fun getImovelPorId(id: Int): Imovel?

    // Salva um novo imóvel, se ja existir, substitiu
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun salvarImovel(imovel: Imovel)

    @Update
    suspend fun atualizarImovel(imovel: Imovel)

    @Delete
    suspend fun deletarImovel(imovel: Imovel)
}