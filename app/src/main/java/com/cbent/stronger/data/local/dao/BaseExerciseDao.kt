package com.cbent.stronger.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cbent.stronger.data.local.entities.BaseExercise
import kotlinx.coroutines.flow.Flow

@Dao
interface BaseExerciseDao {
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertBaseExercise(baseExercise: BaseExercise)

	@Delete suspend fun deleteBaseExercise(baseExercise: BaseExercise)

	@Query("SELECT * FROM base_exercises ORDER BY id DESC")
	fun getAllBaseExercises(): Flow<List<BaseExercise>>
}
