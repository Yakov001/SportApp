package com.example.sportapp.domain.use_case

import com.example.sportapp.domain.SportRepository

class RequestAllGamesUseCase(
    private val repository: SportRepository
) {
    suspend operator fun invoke() = repository.requestAllGames()
}