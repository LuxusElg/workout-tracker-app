package com.cbent.stronger.data.repository

import com.cbent.stronger.data.local.dao.ExerciseDao
import com.cbent.stronger.data.local.entities.Exercise
import kotlinx.coroutines.flow.Flow

class ExerciseRepository(
    private val exerciseDao: ExerciseDao
) {
    val allExercises: Flow<List<Exercise>> = exerciseDao.getAllExercises()

    suspend fun insertExercise(exercise: Exercise) = exerciseDao.insertExercise(exercise)
    suspend fun deleteExercise(exercise: Exercise) = exerciseDao.deleteExercise(exercise)
}