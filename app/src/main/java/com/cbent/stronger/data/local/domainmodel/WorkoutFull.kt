package com.cbent.stronger.data.local.domainmodel

import com.cbent.stronger.data.local.entities.WorkoutTemplate
import java.sql.Timestamp

data class WorkoutFull(
	val id: Long,
	val workoutTemplate: WorkoutTemplate?,
	val name: String,
	val started: Timestamp?,
	val finished: Timestamp?,
	val exercises: List<ExerciseFull>
)
