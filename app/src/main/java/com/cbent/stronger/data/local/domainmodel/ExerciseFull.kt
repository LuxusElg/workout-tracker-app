package com.cbent.stronger.data.local.domainmodel

import com.cbent.stronger.data.local.entities.BaseExercise
import com.cbent.stronger.data.local.entities.Set
import com.cbent.stronger.data.local.entities.Workout
import com.cbent.stronger.enums.MeasurementType

data class ExerciseFull(
	val id: Long,
	val measurementType: MeasurementType,
	val warmupRestTimer: Long?,
	val workRestTimer: Long?,
	val stickyNote: String?,
	val note: String?,
	val sortOrder: Int,
	val workout: Workout,
	val baseExercise: BaseExercise,
	val sets: List<Set>
)
