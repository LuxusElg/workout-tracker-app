package com.cbent.stronger.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "workout_templates")
data class WorkoutTemplate(
	@PrimaryKey(autoGenerate = true)
	val id: Int = 0,
	val name: String
)
