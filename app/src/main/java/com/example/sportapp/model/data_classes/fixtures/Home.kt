package com.example.sportapp.model.data_classes.fixtures

import androidx.room.Ignore

data class Home(
    val id: Int,
    val img: String,
    val name: String,
    val short_code: String,

    @Ignore val coach_id: Any?,
    @Ignore val form: Any?,
    @Ignore val kit_colors: KitColorsX
)