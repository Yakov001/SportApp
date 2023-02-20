package com.example.sportapp.domain.use_case

import com.example.sportapp.domain.SportRepository
import com.example.sportapp.domain.entities.Game

class DeleteGameUseCase(
    private val repository: SportRepository
) {
    suspend operator fun invoke(game: Game) = repository.deleteGame(game)
}