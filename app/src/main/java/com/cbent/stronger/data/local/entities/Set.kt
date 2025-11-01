package com.cbent.stronger.data.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.Index
import androidx.room.PrimaryKey
import com.cbent.stronger.enums.SetType

@Entity(tableName = "sets", foreignKeys = [
	ForeignKey(Exercise::class, ["id"], ["exerciseId"], CASCADE)
], indices = [
	Index(value = ["exerciseId"])
])
data class Set(
	@PrimaryKey(autoGenerate = true)
	val id: Long = 0,
	val exerciseId: Long,
	val reps: Int? = null,
	val timer: Long? = null,
	val weight: Double? = null,
	val setType: SetType = SetType.REGULAR,
	val completed: Boolean = false,
	val sortOrder: Int
)
