package com.cbent.stronger.data.repository

import com.cbent.stronger.data.local.dao.WorkoutDao
import com.cbent.stronger.data.local.domainmodel.WorkoutFull
import com.cbent.stronger.data.local.embeds.ExerciseWithBaseAndSets
import com.cbent.stronger.data.local.embeds.toDomain
import com.cbent.stronger.data.local.entities.Exercise
import com.cbent.stronger.data.local.entities.Set
import com.cbent.stronger.data.local.entities.Workout
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class WorkoutRepository(
    private val workoutDao: WorkoutDao
) {
	val allWorkouts: Flow<List<Workout>> = workoutDao.getAllWorkouts()

	fun getAllWorkoutsFull(): Flow<List<WorkoutFull>> {
		return workoutDao.getAllWorkoutsWithExercisesAndSets()
			.map { workoutList ->
				workoutList.map { it.toDomain() }
			}
	}
	fun exercisesForWorkout(workoutId: Long): Flow<List<ExerciseWithBaseAndSets>> = workoutDao.getExercisesForWorkout(workoutId)
	fun setsForExercise(exerciseId: Long): Flow<List<Set>> = workoutDao.getSetsForExercise(exerciseId)

	suspend fun insertWorkout(workout: Workout) = workoutDao.insertWorkout(workout)
    suspend fun insertExercise(exercise: Exercise) = workoutDao.insertExercise(exercise)
	suspend fun insertSet(set: Set) = workoutDao.insertSet(set)

	suspend fun deleteWorkout(workout: Workout) = workoutDao.deleteWorkout(workout)
    suspend fun deleteExercise(exercise: Exercise) = workoutDao.deleteExercise(exercise)
	suspend fun deleteSet(set: Set) = workoutDao.deleteSet(set)
}
