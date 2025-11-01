package com.cbent.stronger.data.local.embeds

import androidx.room.Embedded
import androidx.room.Relation
import com.cbent.stronger.data.local.domainmodel.ExerciseFull
import com.cbent.stronger.data.local.domainmodel.WorkoutFull
import com.cbent.stronger.data.local.entities.BaseExercise
import com.cbent.stronger.data.local.entities.Exercise
import com.cbent.stronger.data.local.entities.Set
import com.cbent.stronger.data.local.entities.Workout

data class ExerciseWithBaseAndSets(
	@Embedded
	val exercise: Exercise,
	@Relation(
		BaseExercise::class,
		"baseExerciseId",
		"id"
	)
	val baseExercise: BaseExercise,
	@Relation(
		Set::class,
		"id",
		"exerciseId"
	)
	val sets: List<Set>
)

fun ExerciseWithBaseAndSets.toDomain(workout: Workout) = ExerciseFull(
	id = exercise.id,
	measurementType = exercise.measurementType,
	warmupRestTimer = exercise.warmupRestTimer,
	workRestTimer = exercise.workRestTimer,
	stickyNote = exercise.stickyNote,
	note = exercise.note,
	sortOrder = exercise.sortOrder,
	workout = workout,
	baseExercise = baseExercise,
	sets = sets
)
