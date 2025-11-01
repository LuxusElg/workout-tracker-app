package com.cbent.stronger.data.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.Index
import androidx.room.PrimaryKey
import com.cbent.stronger.enums.MeasurementType

@Entity(tableName = "exercises", foreignKeys = [
	ForeignKey(BaseExercise::class, ["id"], ["baseExerciseId"]),
	ForeignKey(Workout::class, ["id"], ["workoutId"], CASCADE)
], indices = [
	Index(value = ["workoutId"]),
	Index(value = ["baseExerciseId"])
])
data class Exercise(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
	val workoutId: Long,
    val measurementType: MeasurementType,
	val baseExerciseId: Long,
	val warmupRestTimer: Long? = null,
	val workRestTimer: Long? = null,
	val stickyNote: String? = null,
	val note: String? = null,
	val sortOrder: Int
)
