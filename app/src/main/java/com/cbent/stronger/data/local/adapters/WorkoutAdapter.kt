package com.cbent.stronger.data.local.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.cbent.stronger.R
import com.cbent.stronger.data.local.entities.Workout

class WorkoutAdapter(
	// TODO: change to WorkoutFull so workouts can list more details in history (best sets etc)
	private val workouts: MutableList<Workout>,
	// TODO: add a vertical dot menu with edit and delete options
	private val onDeleteClicked: (Workout) -> Unit
) : RecyclerView.Adapter<WorkoutAdapter.WorkoutViewHolder>() {

	inner class WorkoutViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
		val nameText: TextView = itemView.findViewById(R.id.workout_name_text)
		val deleteButton: ImageButton = itemView.findViewById(R.id.delete_button)
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkoutViewHolder {
		val view = LayoutInflater.from(parent.context)
			.inflate(R.layout.item_workout_list_singleworkout, parent, false)
		return WorkoutViewHolder(view)
	}

	override fun getItemCount() = workouts.size

	override fun onBindViewHolder(holder: WorkoutViewHolder, position: Int) {
		val workout = workouts[position]
		holder.nameText.text = workout.name

		holder.deleteButton.setOnClickListener {
			onDeleteClicked(workout)
		}
	}

	fun updateWorkouts(newWorkouts: List<Workout>) {
		val diffCallback = WorkoutDiffCallback(workouts, newWorkouts)
		val diffResult = DiffUtil.calculateDiff(diffCallback)

		workouts.clear()
		workouts.addAll(newWorkouts)
		diffResult.dispatchUpdatesTo(this)
	}
}

class WorkoutDiffCallback(
	private val oldList: List<Workout>,
	private val newList: List<Workout>
) : DiffUtil.Callback() {
	override fun getOldListSize() = oldList.size
	override fun getNewListSize() = newList.size
	override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
		oldList[oldItemPosition].id == newList[newItemPosition].id // unique id

	override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
		oldList[oldItemPosition] == newList[newItemPosition] // data equality
}
