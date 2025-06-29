package com.example.gymlog.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.appcompat.app.AppCompatActivity
import com.example.gymlog.data.entity.Exercise
import com.example.gymlog.databinding.FragmentExerciseFormBinding
import com.example.gymlog.ui.viewmodel.ExerciseViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ExerciseFormFragment : Fragment() {
    private var _binding: FragmentExerciseFormBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ExerciseViewModel by viewModels()
    private var exerciseId: Long = -1L
    private var isFavorite: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentExerciseFormBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            exerciseId = it.getLong("exerciseId", -1L)
        }

        updateScreenTitle()

        if (exerciseId != -1L) {
            loadExercise()
            binding.buttonSave.text = "Atualizar"
        }

        setupListeners()
    }

    private fun updateScreenTitle() {
        val title = if (exerciseId != -1L) {
            "Editar Exercício"
        } else {
            "Novo Exercício"
        }
        (activity as? AppCompatActivity)?.supportActionBar?.title = title
    }

    private fun setupListeners() {
        binding.buttonSave.setOnClickListener {
            if (validateFields()) {
                saveExercise()
            }
        }
    }

    private fun loadExercise() {
        lifecycleScope.launch {
            try {
                viewModel.getExerciseById(exerciseId)?.let { exercise ->
                    with(binding) {
                        editTextName.setText(exercise.name)
                        editTextCategory.setText(exercise.category)
                        editTextSeries.setText(exercise.series.toString())
                        editTextReps.setText(exercise.repetitions.toString())
                        editTextNotes.setText(exercise.notes ?: "")
                        isFavorite = exercise.isFavorite // Carrega o estado de favorito
                    }
                }
            } catch (e: Exception) {
                Toast.makeText(context, "Erro ao carregar exercício", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun saveExercise() {
        try {
            val name = binding.editTextName.text.toString()
            val category = binding.editTextCategory.text.toString()
            val series = binding.editTextSeries.text.toString().toIntOrNull() ?: 0
            val reps = binding.editTextReps.text.toString().toIntOrNull() ?: 0
            val notes = binding.editTextNotes.text.toString().takeIf { it.isNotBlank() }

            val exercise = Exercise(
                id = if (exerciseId != -1L) exerciseId else 0L,
                name = name,
                category = category,
                series = series,
                repetitions = reps,
                notes = notes,
                isFavorite = isFavorite // Usa o estado de favorito salvo
            )

            lifecycleScope.launch {
                try {
                    if (exerciseId != -1L) {
                        viewModel.updateExercise(exercise)
                    } else {
                        viewModel.addExercise(exercise)
                    }
                    findNavController().navigateUp()
                } catch (e: Exception) {
                    Toast.makeText(context, "Erro ao salvar exercício: ${e.message}", Toast.LENGTH_LONG).show()
                }
            }
        } catch (e: Exception) {
            Toast.makeText(context, "Erro ao processar dados: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }

    private fun validateFields(): Boolean {
        var isValid = true
        with(binding) {
            if (editTextName.text.isNullOrBlank()) {
                editTextName.error = "Nome é obrigatório"
                isValid = false
            }
            if (editTextCategory.text.isNullOrBlank()) {
                editTextCategory.error = "Categoria é obrigatória"
                isValid = false
            }
            if (editTextSeries.text.isNullOrBlank() || editTextSeries.text.toString().toIntOrNull() == null) {
                editTextSeries.error = "Número de séries inválido"
                isValid = false
            }
            if (editTextReps.text.isNullOrBlank() || editTextReps.text.toString().toIntOrNull() == null) {
                editTextReps.error = "Número de repetições inválido"
                isValid = false
            }
        }
        return isValid
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}