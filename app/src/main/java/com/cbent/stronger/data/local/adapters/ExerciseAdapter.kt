package com.cbent.stronger.data.local.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.cbent.stronger.R
import com.cbent.stronger.data.local.entities.Exercise

class ExerciseAdapter(
    private val exercises: MutableList<Exercise>,
    private val onDeleteClicked: (Exercise) -> Unit
) : RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder>() {

    inner class ExerciseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameText: TextView = itemView.findViewById(R.id.exercise_name_text)
        val deleteButton: ImageButton = itemView.findViewById(R.id.delete_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_exercise, parent, false)
        return ExerciseViewHolder(view)
    }

    override fun getItemCount() = exercises.size

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        val exercise = exercises[position]
        holder.nameText.text = exercise.name

        holder.deleteButton.setOnClickListener {
            onDeleteClicked(exercise)
        }
    }

    fun updateExercises(newExercises: List<Exercise>) {
        val diffCallback = ExerciseDiffCallback(exercises, newExercises)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        exercises.clear()
        exercises.addAll(newExercises)
        diffResult.dispatchUpdatesTo(this)
    }
}

class ExerciseDiffCallback(
    private val oldList: List<Exercise>,
    private val newList: List<Exercise>
) : DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size
    override fun getNewListSize() = newList.size
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition].id == newList[newItemPosition].id // unique id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition] == newList[newItemPosition] // data equality
}