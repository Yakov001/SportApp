package com.example.sportapp.domain.use_case

import com.example.sportapp.domain.SportRepository

class GetSavedGamesUseCase (
    private val repository: SportRepository
) {
    suspend operator fun invoke() = repository.getSavedGames()
}