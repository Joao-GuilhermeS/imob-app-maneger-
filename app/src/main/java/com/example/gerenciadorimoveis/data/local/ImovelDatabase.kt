package com.example.gerenciadorimoveis.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.gerenciadorimoveis.domain.model.Imovel

@Database(entities = [Imovel :: class], version = 1, exportSchema = false)
abstract class ImovelDatabase : RoomDatabase(){
    // localizar as regras de acesso ao banco
    abstract fun imovelDao(): ImovelDao
}
