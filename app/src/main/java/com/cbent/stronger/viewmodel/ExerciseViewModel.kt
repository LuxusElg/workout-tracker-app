package com.cbent.stronger.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.cbent.stronger.data.local.AppDatabase
import com.cbent.stronger.data.repository.ExerciseRepository
import com.cbent.stronger.data.local.entities.Exercise
import com.cbent.stronger.enums.MeasurementSystem
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

    fun addExercise(name: String, measurementSystem: MeasurementSystem) = viewModelScope.launch {
        val exercise = Exercise(name = name, preferredMeasurementSystem = measurementSystem)
        repository.insertExercise(exercise)
    }

    fun deleteExercise(exercise: Exercise) = viewModelScope.launch {
        repository.deleteExercise(exercise)
    }
}