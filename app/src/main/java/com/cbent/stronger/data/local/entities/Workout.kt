package com.cbent.stronger.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Timestamp

@Entity(tableName = "workouts")
data class Workout(
	@PrimaryKey(autoGenerate = true)
	val id: Int = 0,
	val workoutTemplateId: Int? = null,
	val name: String,
	val started: Timestamp? = null,
	val finished: Timestamp? = null
)
