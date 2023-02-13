package com.example.sportapp.model.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.sportapp.model.data_classes.fixtures.Data

@Dao
interface MatchesDao {

    @Query("SELECT * FROM matches")
    suspend fun getSavedMatches() : List<Data>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMatch(match: Data)

}