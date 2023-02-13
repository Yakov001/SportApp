package com.example.sportapp.model.room

import androidx.room.*
import com.example.sportapp.model.data_classes.fixtures.Data

@Dao
interface MatchesDao {

    @Query("SELECT * FROM matches")
    suspend fun getSavedMatches() : List<Data>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMatch(match: Data)

    @Delete
    suspend fun deleteMatch(match: Data)

}