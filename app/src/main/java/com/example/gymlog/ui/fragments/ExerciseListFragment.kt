package com.example.gymlog.ui.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gymlog.R
import com.example.gymlog.databinding.FragmentExerciseListBinding
import com.example.gymlog.model.ExerciseModel
import com.example.gymlog.ui.adapters.ExerciseAdapter
import com.example.gymlog.ui.viewmodel.ExerciseViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ExerciseListFragment : Fragment() {
    private lateinit var binding: FragmentExerciseListBinding
    private val viewModel: ExerciseViewModel by viewModels()

    private val adapter by lazy {
        ExerciseAdapter(
            onExerciseClick = { exercise ->
                val bundle = Bundle().apply {
                    putLong("exerciseId", exercise.id)
                }
                findNavController().navigate(
                    R.id.action_list_to_add,
                    bundle
                )
            },
            onDeleteClick = { exercise ->
                confirmarExclusao(exercise)
            },
            onFavoriteClick = { exercise ->
                viewModel.toggleFavorite(exercise)
            }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentExerciseListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupSearchView()
        setupFab()
        observeExercises()
    }

    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            adapter = this@ExerciseListFragment.adapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun setupSearchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { viewModel.setSearchQuery(it) }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { viewModel.setSearchQuery(it) }
                return true
            }
        })
    }

    private fun setupFab() {
        binding.fabAdd.setOnClickListener {
            findNavController().navigate(R.id.action_list_to_add)
        }
    }

    private fun observeExercises() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.exercises.collect { exercises ->
                adapter.submitList(exercises)
            }
        }
    }

    private fun confirmarExclusao(exercise: ExerciseModel) {
        AlertDialog.Builder(requireContext())
            .setTitle("Confirmar exclusão")
            .setMessage("Deseja realmente excluir o exercício ${exercise.name}?")
            .setPositiveButton("Sim") { _, _ ->
                viewModel.deleteExercise(exercise)
            }
            .setNegativeButton("Não", null)
            .show()
    }
}