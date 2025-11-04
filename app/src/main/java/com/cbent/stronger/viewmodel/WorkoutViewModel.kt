package com.cbent.stronger.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.cbent.stronger.data.local.AppDatabase
import com.cbent.stronger.data.local.domainmodel.ExerciseFull
import com.cbent.stronger.data.local.domainmodel.WorkoutFull
import com.cbent.stronger.data.local.entities.BaseExercise
import com.cbent.stronger.data.local.entities.Exercise
import com.cbent.stronger.data.local.entities.ExerciseTemplate
import com.cbent.stronger.data.local.entities.TrainingSet
import com.cbent.stronger.data.repository.WorkoutRepository
import com.cbent.stronger.enums.ExerciseType
import com.cbent.stronger.enums.TrackingType
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class WorkoutViewModel(application: Application, workoutId: Long) : AndroidViewModel(application) {
	private val workoutDao = AppDatabase.getInstance(application).workoutDao()
	private val repository = WorkoutRepository(workoutDao)

	val workout = repository.getWorkoutFull(workoutId).stateIn(
		viewModelScope,
		SharingStarted.WhileSubscribed(5000),
		null
	)

    fun addExerciseFromBase(workout: WorkoutFull, baseExercise: BaseExercise) = viewModelScope.launch {
		val exerciseCount = workout.exercises.count()
        val exercise = Exercise(
			workoutId = workout.id,
			measurementType = baseExercise.measurementType,
			baseExerciseId = baseExercise.id,
			warmupRestTimer = baseExercise.warmupRestTimer,
			workRestTimer = baseExercise.workRestTimer,
			sortOrder = exerciseCount
		)
        repository.insertExercise(exercise)
    }

	fun addSetToExercise(exercise: ExerciseFull) = viewModelScope.launch {
		val setCount = exercise.sets.count()
		val trainingSet = TrainingSet(
			exerciseId = exercise.id,
			reps = if (exercise.baseExercise.trackingType == TrackingType.TIMER) null else 0,
			timer = if (exercise.baseExercise.trackingType == TrackingType.REPS) null else 60,
			weight = if (exercise.baseExercise.exerciseType == ExerciseType.CARDIO || exercise.baseExercise.exerciseType == ExerciseType.BODYWEIGHT) null else 0.0,
			sortOrder = setCount
		)
		repository.insertSet(trainingSet)
	}

	// TODO: move this to WorkoutTemplateViewModel, createWorkoutFromTemplate (or where ever that ends up being put)
	fun addExerciseFromTemplate(workout: WorkoutFull, exerciseTemplate: ExerciseTemplate) = viewModelScope.launch {
		val exerciseCount = workout.exercises.count()
		val exercise = Exercise(
			workoutId = workout.id,
			measurementType = exerciseTemplate.measurementType,
			baseExerciseId = exerciseTemplate.baseExerciseId,
			warmupRestTimer = exerciseTemplate.warmupRestTimer,
			workRestTimer = exerciseTemplate.workRestTimer,
			stickyNote = exerciseTemplate.stickyNote,
			sortOrder = exerciseCount
		)
		repository.insertExercise(exercise)
	}

    fun deleteExercise(exercise: Exercise) = viewModelScope.launch {
        repository.deleteExercise(exercise)
    }

	fun deleteSet(trainingSet: TrainingSet) = viewModelScope.launch {
		repository.deleteSet(trainingSet)
	}
}
