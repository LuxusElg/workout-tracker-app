package com.cbent.stronger.data.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.ForeignKey.Companion.CASCADE
import com.cbent.stronger.enums.MeasurementType

@Entity(tableName = "exercises", foreignKeys = [
	ForeignKey(BaseExercise::class, ["id"], ["baseExerciseId"]),
	ForeignKey(WorkoutTemplate::class, ["id"], ["workoutTemplateId"], CASCADE)
])
data class ExerciseTemplate(
	@PrimaryKey(autoGenerate = true)
	val id: Int = 0,
	val workoutTemplateId: Int,
	val measurementType: MeasurementType,
	val baseExerciseId: Int,
	val warmupRestTimer: Int? = null,
	val workRestTimer: Int? = null,
	val stickyNote: String? = null
)
