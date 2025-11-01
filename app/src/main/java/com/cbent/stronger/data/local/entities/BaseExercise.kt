package com.cbent.stronger.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cbent.stronger.enums.ExerciseType
import com.cbent.stronger.enums.MeasurementType
import com.cbent.stronger.enums.TrackingType

@Entity(tableName = "base_exercises")
data class BaseExercise(
	@PrimaryKey(autoGenerate = true)
	val id: Long = 0,
	val measurementType: MeasurementType,
	val exerciseType: ExerciseType,
	val warmupRestTimer: Long? = null,
	val workRestTimer: Long? = null,
	val trackingType: TrackingType,
	val customWeight: Double? = null,
	val stickyNote: String? = null,
	val unilateral: Boolean = false
)
