package com.example.sportapp.model.data_classes.fixtures

import androidx.room.Ignore

data class Time(
    val date: String,
    val datetime: String,
    @Ignore val minute: Any? = null,
    val time: String,
    val timestamp: Int,
    val timezone: String
)