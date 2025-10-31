package com.cbent.stronger.data.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey
import com.cbent.stronger.enums.SetType

@Entity(tableName = "set_templates", foreignKeys = [
	ForeignKey(ExerciseTemplate::class, ["id"], ["exerciseTemplateId"], CASCADE)
])
data class SetTemplate(
	@PrimaryKey(autoGenerate = true)
	val id: Int = 0,
	val exerciseTemplateId: Int,
	val reps: Int? = null,
	val timer: Int? = null,
	val weight: Int? = null,
	val setType: SetType = SetType.REGULAR
)
