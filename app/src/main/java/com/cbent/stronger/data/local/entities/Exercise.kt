package com.cbent.stronger.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cbent.stronger.enums.MeasurementSystem

@Entity(tableName = "exercises")
data class Exercise(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val preferredMeasurementSystem: MeasurementSystem
)
