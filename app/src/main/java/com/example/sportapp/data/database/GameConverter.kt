package com.example.sportapp.data.database

import androidx.room.TypeConverter
import com.example.sportapp.data.database.entities.LeagueDbModel
import com.example.sportapp.data.database.entities.TeamDbModel
import com.google.gson.Gson


class GameConverter {

    @TypeConverter
    fun fromLeague(league: LeagueDbModel): String {
        return Gson().toJson(league)
    }

    @TypeConverter
    fun toLeague(string: String): LeagueDbModel {
        return Gson().fromJson(string, LeagueDbModel::class.java)
    }

    @TypeConverter
    fun fromTeams(teams: TeamDbModel): String {
        return Gson().toJson(teams)
    }

    @TypeConverter
    fun toTeams(string: String): TeamDbModel {
        return Gson().fromJson(string, TeamDbModel::class.java)
    }
}