package com.example.sportapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.sportapp.data.database.entities.GameDbModel

@Database(entities = [GameDbModel::class], version = 2)
@TypeConverters(GameConverter::class)
abstract class GameDatabase : RoomDatabase() {

    abstract fun getGameDao(): GameDao

    companion object {
        @Volatile
        private var instance: GameDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                GameDatabase::class.java,
                "matches_db.db"
            )
                .fallbackToDestructiveMigration()
                .build()
    }
}