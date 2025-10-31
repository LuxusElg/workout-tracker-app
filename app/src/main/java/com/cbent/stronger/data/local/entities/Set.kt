package com.cbent.stronger.data.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey
import com.cbent.stronger.enums.SetType

@Entity(tableName = "sets", foreignKeys = [
	ForeignKey(Exercise::class, ["id"], ["exerciseId"], CASCADE)
])
data class Set(
	@PrimaryKey(autoGenerate = true)
	val id: Int = 0,
	val exerciseId: Int,
	val reps: Int? = null,
	val timer: Int? = null,
	val weight: Int? = null,
	val setType: SetType = SetType.REGULAR,
	val completed: Boolean = false
)
