package com.example.sportapp.domain

import com.example.sportapp.domain.entities.Game

interface SportRepository {

    suspend fun requestAllGames() : Resource<List<List<Game>>>

    suspend fun getSavedGames() : List<Game>

    suspend fun saveGame(game: Game)

    suspend fun deleteGame(game: Game)
}