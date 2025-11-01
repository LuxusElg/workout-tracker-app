package com.cbent.stronger.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.cbent.stronger.data.local.AppDatabase
import com.cbent.stronger.data.local.entities.Workout
import com.cbent.stronger.data.repository.WorkoutRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class WorkoutListViewModel(application: Application): AndroidViewModel(application) {
	private val workoutDao = AppDatabase.getInstance(application).workoutDao()
	private val repository = WorkoutRepository(workoutDao)

	val allWorkouts = repository.allWorkouts.stateIn(
		viewModelScope,
		SharingStarted.WhileSubscribed(5000),
		emptyList()
	)

	fun addEmptyWorkout(workoutName: String) = viewModelScope.launch {
		val workout = Workout(
			name = workoutName
		)
		repository.insertWorkout(workout)
	}

	fun deleteWorkout(workout: Workout) = viewModelScope.launch {
		repository.deleteWorkout(workout)
	}
}
