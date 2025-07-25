package com.example.gymlog.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gymlog.model.ExerciseModel
import com.example.gymlog.data.repository.ExerciseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExerciseViewModel @Inject constructor(
    private val repository: ExerciseRepository
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    @OptIn(ExperimentalCoroutinesApi::class)
    val exercises = _searchQuery.flatMapLatest { query ->
        if (query.isEmpty()) {
            repository.getAllExercises()
        } else {
            repository.searchExercises(query)
        }
    }.stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun addExercise(exercise: ExerciseModel) = viewModelScope.launch {
        repository.insertExercise(exercise)
    }

    fun updateExercise(exercise: ExerciseModel) = viewModelScope.launch {
        repository.updateExercise(exercise)
    }

    fun deleteExercise(exercise: ExerciseModel) = viewModelScope.launch {
        repository.deleteExercise(exercise)
    }

    fun toggleFavorite(exercise: ExerciseModel) = viewModelScope.launch {
        repository.toggleFavorite(exercise)
    }

    suspend fun getExerciseById(id: Long): ExerciseModel? {
        return repository.getExerciseById(id)
    }
}