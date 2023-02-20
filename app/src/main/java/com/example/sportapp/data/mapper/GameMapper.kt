package com.example.sportapp.data.mapper

import com.example.sportapp.data.database.entities.GameDbModel
import com.example.sportapp.data.database.entities.LeagueDbModel
import com.example.sportapp.data.database.entities.TeamDbModel
import com.example.sportapp.data.network.entities.fixtures.*
import com.example.sportapp.data.network.entities.fixtures.League
import com.example.sportapp.domain.entities.*

class GameMapper {

    fun mapDtoToDbModel(dto: GameDto) : GameDbModel {
        return GameDbModel(
            id = dto.id,
            league = LeagueDbModel(
                name = dto.league.name,
                country_name = dto.league.country_name,
                country_flag = dto.league.country_flag
            ),
            venueId = dto.venue_id,
            stageId = dto.stage_id,
            stageName = dto.stage_name,
            roundName = dto.round_name,

            time = dto.time.time,
            dateTime = dto.time.datetime,
            timeStamp = dto.time.timestamp,

            homeTeam = TeamDbModel(
                name = dto.teams.home.name,
                image = dto.teams.home.img
            ),
            awayTeam = TeamDbModel(
                name = dto.teams.away.name,
                image = dto.teams.away.img
            )
        )
    }

    fun mapDbModelToDto(dbModel : GameDbModel) : GameDto {
        return GameDto(
            id = dbModel.id,
            league = League(
                country_flag = dbModel.league.country_flag,
                country_name = dbModel.league.country_name,
                name = dbModel.league.name
            ),
            teams = Teams(
                away = Away(
                    img = dbModel.awayTeam.image,
                    name = dbModel.awayTeam.name
                ),
                home = Home(
                    img = dbModel.homeTeam.image,
                    name = dbModel.homeTeam.name
                )
            ),
            venue_id = dbModel.venueId,
            stage_id = dbModel.stageId,
            stage_name = dbModel.stageName,
            time = Time(
                time = dbModel.time,
                datetime = dbModel.dateTime,
                timestamp = dbModel.timeStamp
            ),
            round_name = dbModel.roundName
        )
    }

    fun mapDtoToEntity(dto: GameDto) : Game {
        return Game(
            id = dto.id,
            league = com.example.sportapp.domain.entities.League(
                name = dto.league.name,
                country_name = dto.league.country_name,
                country_flag = dto.league.country_flag
            ),
            venueId = dto.venue_id,
            stageId = dto.stage_id,
            stageName = dto.stage_name,
            roundName = dto.round_name,

            time = dto.time.time,
            dateTime = dto.time.datetime,
            timeStamp = dto.time.timestamp,

            homeTeam = Team(
                name = dto.teams.home.name,
                image = dto.teams.home.img
            ),
            awayTeam = Team(
                name = dto.teams.away.name,
                image = dto.teams.away.img
            )
        )
    }

    fun mapDbModelToEntity(dbModel: GameDbModel) : Game {
        val mapper = GameMapper()
        val dto = mapper.mapDbModelToDto(dbModel)
        return mapper.mapDtoToEntity(dto)
    }

    fun mapEntityToDbModel(game : Game) : GameDbModel {
        return GameDbModel(
            id = game.id,
            league = LeagueDbModel(
                name = game.league.name,
                country_name = game.league.country_name,
                country_flag = game.league.country_flag
            ),
            venueId = game.venueId,
            stageId = game.stageId,
            stageName = game.stageName,
            roundName = game.roundName,

            time = game.time,
            dateTime = game.dateTime,
            timeStamp = game.timeStamp,

            homeTeam = TeamDbModel(
                name = game.homeTeam.name,
                image = game.homeTeam.name
            ),
            awayTeam = TeamDbModel(
                name = game.awayTeam.name,
                image = game.awayTeam.image
            )
        )
    }
}