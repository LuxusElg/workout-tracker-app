package com.cbent.stronger.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.cbent.stronger.data.local.AppDatabase
import com.cbent.stronger.data.local.entities.BaseExercise
import com.cbent.stronger.data.repository.ExerciseRepository
import com.cbent.stronger.data.local.entities.Exercise
import com.cbent.stronger.data.local.entities.ExerciseTemplate
import com.cbent.stronger.data.local.entities.Workout
import com.cbent.stronger.enums.MeasurementType
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ExerciseViewModel(application: Application) : AndroidViewModel(application) {
    private val exerciseDao = AppDatabase.getInstance(application).exerciseDao()
    private val repository = ExerciseRepository(exerciseDao)

    val allExercises = repository.allExercises.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptyList()
    )

    fun addExerciseFromBase(workout: Workout, baseExercise: BaseExercise) = viewModelScope.launch {
        val exercise = Exercise(
			workoutId = workout.id,
			measurementType = baseExercise.measurementType,
			baseExerciseId = baseExercise.id,
			warmupRestTimer = baseExercise.warmupRestTimer,
			workRestTimer = baseExercise.workRestTimer,
		)
        repository.insertExercise(exercise)
    }

	fun addExerciseFromTemplate(workout: Workout, exerciseTemplate: ExerciseTemplate) = viewModelScope.launch {
		val exercise = Exercise(
			workoutId = workout.id,
			measurementType = exerciseTemplate.measurementType,
			baseExerciseId = exerciseTemplate.baseExerciseId,
			warmupRestTimer = exerciseTemplate.warmupRestTimer,
			workRestTimer = exerciseTemplate.workRestTimer,
			stickyNote = exerciseTemplate.stickyNote
		)
		repository.insertExercise(exercise)
	}

    fun deleteExercise(exercise: Exercise) = viewModelScope.launch {
        repository.deleteExercise(exercise)
    }
}
