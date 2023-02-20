package com.example.sportapp.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "matches")
data class GameDbModel(
    @PrimaryKey
    val id: Int,
    val league: LeagueDbModel,
    val venueId: String,
    val stageId: String,
    val stageName: String,
    val roundName: String,

    val time: String,
    val dateTime : String,
    val timeStamp : Int,

    val homeTeam: TeamDbModel,
    val awayTeam: TeamDbModel
)
