package com.cbent.stronger.data.local

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import com.cbent.stronger.data.local.dao.ExerciseDao
import com.cbent.stronger.data.local.entities.Exercise

@Database(entities = [Exercise::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun exerciseDao(): ExerciseDao

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