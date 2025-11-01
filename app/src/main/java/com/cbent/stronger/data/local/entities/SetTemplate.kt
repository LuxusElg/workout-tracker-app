package com.cbent.stronger.data.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.Index
import androidx.room.PrimaryKey
import com.cbent.stronger.enums.SetType

@Entity(tableName = "set_templates", foreignKeys = [
	ForeignKey(ExerciseTemplate::class, ["id"], ["exerciseTemplateId"], CASCADE)
], indices = [
	Index(value = ["exerciseTemplateId"])
])
data class SetTemplate(
	@PrimaryKey(autoGenerate = true)
	val id: Long = 0,
	val exerciseTemplateId: Long,
	val reps: Int? = null,
	val timer: Long? = null,
	val weight: Double? = null,
	val setType: SetType = SetType.REGULAR,
	val sortOrder: Int
)
