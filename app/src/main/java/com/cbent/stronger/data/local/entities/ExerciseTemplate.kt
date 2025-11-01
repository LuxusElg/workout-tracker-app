package com.cbent.stronger.data.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.Index
import com.cbent.stronger.enums.MeasurementType

@Entity(tableName = "exercises", foreignKeys = [
	ForeignKey(BaseExercise::class, ["id"], ["baseExerciseId"]),
	ForeignKey(WorkoutTemplate::class, ["id"], ["workoutTemplateId"], CASCADE)
], indices = [
	Index(value = ["workoutTemplateId"]),
	Index(value = ["baseExerciseId"])
])
data class ExerciseTemplate(
	@PrimaryKey(autoGenerate = true)
	val id: Long = 0,
	val workoutTemplateId: Long,
	val measurementType: MeasurementType,
	val baseExerciseId: Long,
	val warmupRestTimer: Long? = null,
	val workRestTimer: Long? = null,
	val stickyNote: String? = null,
	val sortOrder: Int
)
