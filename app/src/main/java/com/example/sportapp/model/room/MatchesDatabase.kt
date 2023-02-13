package com.example.sportapp.model.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.sportapp.model.data_classes.fixtures.Data

@Database(entities = [Data::class], version = 1)
@TypeConverters(MatchConverter::class)
abstract class MatchesDatabase : RoomDatabase(){

    abstract fun getMatchesDao(): MatchesDao

    companion object {
        @Volatile
        private var instance: MatchesDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also{ instance = it }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                MatchesDatabase::class.java,
                "matches_db.db"
            ).build()
    }
}