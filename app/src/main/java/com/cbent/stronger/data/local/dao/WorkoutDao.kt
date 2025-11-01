package com.cbent.stronger.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.cbent.stronger.data.local.embeds.ExerciseWithBaseAndSets
import com.cbent.stronger.data.local.embeds.WorkoutWithExercisesAndSets
import com.cbent.stronger.data.local.entities.Exercise
import com.cbent.stronger.data.local.entities.Set
import com.cbent.stronger.data.local.entities.Workout
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkoutDao {

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertWorkout(workout: Workout)
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertExercise(exercise: Exercise)
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertSet(set: Set)

	@Delete suspend fun deleteWorkout(workout: Workout)
	@Delete suspend fun deleteExercise(exercise: Exercise)
	@Delete suspend fun deleteSet(set: Set)

	@Query("SELECT * FROM workouts ORDER BY id DESC")
	fun getAllWorkouts(): Flow<List<Workout>>

	@Transaction
	@Query("SELECT * FROM workouts ORDER BY id DESC")
	fun getAllWorkoutsWithExercisesAndSets(): Flow<List<WorkoutWithExercisesAndSets>>

	@Transaction
	@Query("SELECT * FROM exercises WHERE workoutId = :workoutId")
	fun getExercisesForWorkout(workoutId: Long): Flow<List<ExerciseWithBaseAndSets>>

	@Query("SELECT * FROM sets WHERE exerciseId = :exerciseId")
	fun getSetsForExercise(exerciseId: Long): Flow<List<Set>>
}
