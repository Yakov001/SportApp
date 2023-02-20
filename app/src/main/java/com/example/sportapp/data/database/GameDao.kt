package com.example.sportapp.data.database

import androidx.room.*
import com.example.sportapp.data.database.entities.GameDbModel

@Dao
interface GameDao {

    @Query("SELECT * FROM matches")
    suspend fun getSavedGames() : List<GameDbModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveGame(game: GameDbModel)

    @Delete
    suspend fun deleteGame(game: GameDbModel)

}