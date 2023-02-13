package com.example.sportapp.model.room

import androidx.room.TypeConverter
import com.example.sportapp.model.data_classes.fixtures.League
import com.example.sportapp.model.data_classes.fixtures.Teams
import com.example.sportapp.model.data_classes.fixtures.Time
import com.google.gson.Gson


class MatchConverter {

    @TypeConverter
    fun fromLeague(league: League): String {
        return "${league.country_code}>${league.country_flag}>${league.country_id}>${league.country_name}>${league.id}>${league.name}>${league.type}"
    }

    @TypeConverter
    fun toLeague(string: String) : League {
        val s = string.split('>')
        return League(
            s[0], s[1], s[2], s[3], s[4].toInt(), s[5], s[6]
        )
    }

    /*@TypeConverter
    fun fromTeams(teams: Teams) : String {
        return "${teams.home.id}>${teams.home.img}>${teams.home.name}>${teams.home.short_code}<${teams.away.id}>${teams.away.img}>${teams.away.name}>${teams.away.short_code}"
    }*/

    @TypeConverter
    fun fromTeams(teams: Teams) : String {
        return Gson().toJson(teams)
    }

    @TypeConverter
    fun toTeams(string: String) : Teams {
        return Gson().fromJson(string, Teams::class.java)
    }

    @TypeConverter
    fun fromTime(time: Time) : String {
        return "${time.date}>${time.datetime}>${time.time}>${time.timestamp}>${time.timezone}"
    }

    @TypeConverter
    fun toTime(string: String) : Time {
        val s = string.split('>')
        return Time(date = s[0], datetime = s[1], time = s[2], timestamp = s[3].toInt(), timezone = s[4])
    }
}