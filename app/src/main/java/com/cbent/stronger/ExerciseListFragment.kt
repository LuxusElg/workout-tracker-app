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
import com.cbent.stronger.data.local.adapters.ExerciseAdapter
import com.cbent.stronger.databinding.FragmentExerciseListBinding
import com.cbent.stronger.enums.MeasurementType
import com.cbent.stronger.viewmodel.ExerciseViewModel
import kotlinx.coroutines.launch

class ExerciseListFragment : Fragment() {

    private var _binding: FragmentExerciseListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val exerciseViewModel: ExerciseViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView
    private lateinit var exerciseAdapter: ExerciseAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentExerciseListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recycler_exercise_list)
        exerciseAdapter = ExerciseAdapter(mutableListOf()) {
            exercise -> exerciseViewModel.deleteExercise(exercise)
        }

        recyclerView.adapter = exerciseAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewLifecycleOwner.lifecycleScope.launch {
            exerciseViewModel.allExercises.collect { exercises ->
                exerciseAdapter.updateExercises(exercises)
            }
        }
        binding.buttonCreateExercise.setOnClickListener {
            exerciseViewModel.addExercise("TestExercise", MeasurementType.METRIC)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
