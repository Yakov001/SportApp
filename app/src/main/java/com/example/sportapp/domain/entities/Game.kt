package com.example.sportapp.domain.entities

data class Game(
    val id: Int,
    val league: League,
    val venueId: String,
    val stageId: String,
    val stageName: String,
    val roundName: String,

    val time: String,
    val dateTime : String,
    val timeStamp : Int,

    val homeTeam: Team,
    val awayTeam: Team
)
