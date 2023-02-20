package com.example.sportapp.data.network.entities.fixtures

data class GameDto(
    val id: Int,
    val league: League,
    val teams: Teams,
    val venue_id: String,
    val stage_id: String,
    val stage_name: String,
    val time: Time,
    val round_name: String
)
