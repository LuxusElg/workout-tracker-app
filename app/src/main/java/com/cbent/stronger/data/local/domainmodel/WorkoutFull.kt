package com.cbent.stronger.data.local.domainmodel

import com.cbent.stronger.data.local.entities.WorkoutTemplate
import java.sql.Timestamp

data class WorkoutFull(
	val id: Long,
	val workoutTemplate: WorkoutTemplate?,
	val name: String,
	val started: Long,
	val finished: Long?,
	val exercises: List<ExerciseFull>
)
