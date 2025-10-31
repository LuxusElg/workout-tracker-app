package com.cbent.stronger.data.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey
import com.cbent.stronger.enums.MeasurementType

@Entity(tableName = "exercises", foreignKeys = [
	ForeignKey(BaseExercise::class, ["id"], ["baseExerciseId"]),
	ForeignKey(Workout::class, ["id"], ["workoutId"], CASCADE)
])
data class Exercise(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
	val workoutId: Int,
    val measurementType: MeasurementType,
	val baseExerciseId: Int,
	val warmupRestTimer: Int? = null,
	val workRestTimer: Int? = null,
	val stickyNote: String? = null,
	val note: String? = null
)
