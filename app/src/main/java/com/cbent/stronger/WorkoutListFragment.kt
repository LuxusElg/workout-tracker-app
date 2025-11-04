package com.cbent.stronger

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cbent.stronger.data.local.adapters.WorkoutAdapter
import com.cbent.stronger.databinding.FragmentWorkoutListBinding
import com.cbent.stronger.viewmodel.WorkoutListViewModel
import kotlinx.coroutines.launch

class WorkoutListFragment : Fragment() {

	private var _binding: FragmentWorkoutListBinding? = null

	// This property is only valid between onCreateView and
	// onDestroyView.
	private val binding get() = _binding!!

	private val workoutListViewModel: WorkoutListViewModel by viewModels()
	private lateinit var recyclerView: RecyclerView
	private lateinit var workoutAdapter: WorkoutAdapter

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		_binding = FragmentWorkoutListBinding.inflate(inflater, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		recyclerView = view.findViewById(R.id.recycler_workout_list)
		workoutAdapter = WorkoutAdapter(mutableListOf()) {
				workout -> workoutListViewModel.deleteWorkout(workout)
		}

		recyclerView.adapter = workoutAdapter
		recyclerView.layoutManager = LinearLayoutManager(requireContext())

		viewLifecycleOwner.lifecycleScope.launch {
			workoutListViewModel.allWorkouts.collect { workouts ->
				workoutAdapter.updateWorkouts(workouts)
			}
		}
		binding.buttonStartBlankWorkout.setOnClickListener {
			//exerciseViewModel.addExercise("TestExercise", MeasurementType.METRIC)
		}

	}

	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}
}
