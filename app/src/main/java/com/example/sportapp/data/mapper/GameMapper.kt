package com.example.sportapp.data.mapper

import com.example.sportapp.data.database.entities.GameDbModel
import com.example.sportapp.data.database.entities.LeagueDbModel
import com.example.sportapp.data.database.entities.TeamDbModel
import com.example.sportapp.data.network.entities.fixtures.*
import com.example.sportapp.data.network.entities.fixtures.League
import com.example.sportapp.domain.entities.*

fun GameDto.toDbModel(): GameDbModel {
    return GameDbModel(
        id = this.id,
        league = LeagueDbModel(
            name = this.league.name,
            country_name = this.league.country_name,
            country_flag = this.league.country_flag
        ),
        venueId = this.venue_id,
        stageId = this.stage_id,
        stageName = this.stage_name,
        roundName = this.round_name,

        time = this.time.time,
        dateTime = this.time.datetime,
        timeStamp = this.time.timestamp,

        homeTeam = TeamDbModel(
            name = this.teams.home.name,
            image = this.teams.home.img
        ),
        awayTeam = TeamDbModel(
            name = this.teams.away.name,
            image = this.teams.away.img
        )
    )
}

fun GameDbModel.toDto(): GameDto {
    return GameDto(
        id = this.id,
        league = League(
            country_flag = this.league.country_flag,
            country_name = this.league.country_name,
            name = this.league.name
        ),
        teams = Teams(
            away = Away(
                img = this.awayTeam.image,
                name = this.awayTeam.name
            ),
            home = Home(
                img = this.homeTeam.image,
                name = this.homeTeam.name
            )
        ),
        venue_id = this.venueId,
        stage_id = this.stageId,
        stage_name = this.stageName,
        time = Time(
            time = this.time,
            datetime = this.dateTime,
            timestamp = this.timeStamp
        ),
        round_name = this.roundName
    )
}

fun GameDto.toEntity(): Game {
    return Game(
        id = this.id,
        league = com.example.sportapp.domain.entities.League(
            name = this.league.name,
            country_name = this.league.country_name,
            country_flag = this.league.country_flag
        ),
        venueId = this.venue_id,
        stageId = this.stage_id,
        stageName = this.stage_name,
        roundName = this.round_name,

        time = this.time.time,
        dateTime = this.time.datetime,
        timeStamp = this.time.timestamp,

        homeTeam = Team(
            name = this.teams.home.name,
            image = this.teams.home.img
        ),
        awayTeam = Team(
            name = this.teams.away.name,
            image = this.teams.away.img
        )
    )
}

fun GameDbModel.toEntity(): Game {
    val dto = this.toDto()
    return dto.toEntity()
}

fun Game.toDbModel(): GameDbModel {
    return GameDbModel(
        id = this.id,
        league = LeagueDbModel(
            name = this.league.name,
            country_name = this.league.country_name,
            country_flag = this.league.country_flag
        ),
        venueId = this.venueId,
        stageId = this.stageId,
        stageName = this.stageName,
        roundName = this.roundName,

        time = this.time,
        dateTime = this.dateTime,
        timeStamp = this.timeStamp,

        homeTeam = TeamDbModel(
            name = this.homeTeam.name,
            image = this.homeTeam.image
        ),
        awayTeam = TeamDbModel(
            name = this.awayTeam.name,
            image = this.awayTeam.image
        )
    )
}