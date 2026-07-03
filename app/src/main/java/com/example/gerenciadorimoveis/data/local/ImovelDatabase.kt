package com.example.gerenciadorimoveis.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.gerenciadorimoveis.domain.model.Imovel

@Database(entities = [Imovel::class], version = 1, exportSchema = false)
abstract class ImovelDatabase : RoomDatabase() {

    abstract fun imovelDao(): ImovelDao

    companion object {
        @Volatile
        private var INSTANCE: ImovelDatabase? = null

        // funcao app navigation busca
        fun getDatabase(context: Context): ImovelDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ImovelDatabase::class.java,
                    "imovel_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}