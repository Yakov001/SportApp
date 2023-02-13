package com.example.sportapp.model.data_classes.fixtures

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.sportapp.model.room.MatchConverter

@Entity(tableName = "matches")
data class Data(
    @PrimaryKey
    val id: Int,
    val league: League,
    val teams: Teams,
    val venue_id: String,
    val stage_id: String,
    val stage_name: String,
    val time: Time,
    val round_name: String
    ) {
    @Ignore val aggregate_id: Any? = null
    @Ignore val assistants: Assistants? = null
    @Ignore val attendance: Any? = null
    @Ignore val coverage: Coverage? = null
    @Ignore val deleted: String? = null
    @Ignore val group_id: String? = null
    @Ignore val group_name: String? = null
    @Ignore val info: Any? = null
    @Ignore val leg: Any? = null
    @Ignore val pitch: Any? = null
    @Ignore val referee_id: Any? = null
    @Ignore val round_id: String? = null
    @Ignore val scores: Scores? = null
    @Ignore val season_id: String? = null
    @Ignore val season_name: String? = null
    @Ignore val standings: Standings? = null
    @Ignore val status: Int? = null
    @Ignore val status_name: String? = null
    @Ignore val status_period: Any? = null
    @Ignore val weather_report: Any? = null
    @Ignore val week: String? = null
    @Ignore val winner_team_id: String? = null
}
