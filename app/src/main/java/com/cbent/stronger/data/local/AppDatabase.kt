package com.cbent.stronger.data.local

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import com.cbent.stronger.data.local.dao.BaseExerciseDao
import com.cbent.stronger.data.local.dao.WorkoutDao
import com.cbent.stronger.data.local.entities.BaseExercise
import com.cbent.stronger.data.local.entities.Exercise
import com.cbent.stronger.data.local.entities.ExerciseTemplate
import com.cbent.stronger.data.local.entities.SetTemplate
import com.cbent.stronger.data.local.entities.TrainingSet
import com.cbent.stronger.data.local.entities.Workout
import com.cbent.stronger.data.local.entities.WorkoutTemplate

@Database(entities = [
	BaseExercise::class,
	Exercise::class,
	ExerciseTemplate::class,
	TrainingSet::class,
	SetTemplate::class,
	Workout::class,
	WorkoutTemplate::class
 ], version = 1)
abstract class AppDatabase : RoomDatabase() {
	abstract fun workoutDao(): WorkoutDao
	abstract fun baseExerciseDao(): BaseExerciseDao

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "stronger_db"
                )
                    .build()
                    .also { INSTANCE = it }
            }
    }
}
