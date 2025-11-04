package com.cbent.stronger.data.local.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "workouts", indices = [
	Index(value = ["workoutTemplateId"])
])
data class Workout(
	@PrimaryKey(autoGenerate = true)
	val id: Long = 0,
	val workoutTemplateId: Long? = null,
	val name: String,
	val started: Long = System.currentTimeMillis(),
	val finished: Long? = null
)
