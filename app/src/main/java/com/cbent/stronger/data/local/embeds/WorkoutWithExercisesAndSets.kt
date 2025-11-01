package com.cbent.stronger.data.local.embeds

import androidx.room.Embedded
import androidx.room.Relation
import com.cbent.stronger.data.local.domainmodel.WorkoutFull
import com.cbent.stronger.data.local.entities.Exercise
import com.cbent.stronger.data.local.entities.Workout
import com.cbent.stronger.data.local.entities.WorkoutTemplate

data class WorkoutWithExercisesAndSets(
	@Embedded
	val workout: Workout,
	@Relation(
		Exercise::class,
		"id",
		"workoutId"
	)
	val exercises: List<ExerciseWithBaseAndSets>
)

fun WorkoutWithExercisesAndSets.toDomain(workoutTemplate: WorkoutTemplate? = null) = WorkoutFull(
	id = workout.id,
	workoutTemplate = workoutTemplate,
	name = workout.name,
	started = workout.started,
	finished = workout.finished,
	exercises = exercises.map { it.toDomain(workout) }
)
