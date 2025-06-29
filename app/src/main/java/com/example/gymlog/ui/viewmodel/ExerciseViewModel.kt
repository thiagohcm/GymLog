package com.example.gymlog.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gymlog.data.entity.Exercise
import com.example.gymlog.data.ExerciseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExerciseViewModel @Inject constructor(
    private val repository: ExerciseRepository
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
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

    fun addExercise(exercise: Exercise) = viewModelScope.launch {
        repository.insertExercise(exercise)
    }

    fun updateExercise(exercise: Exercise) = viewModelScope.launch {
        repository.updateExercise(exercise)
    }

    fun deleteExercise(exercise: Exercise) = viewModelScope.launch {
        repository.deleteExercise(exercise)
    }

    fun toggleFavorite(exercise: Exercise) = viewModelScope.launch {
        repository.toggleFavorite(exercise)
    }

    suspend fun getExerciseById(id: Long): Exercise? {
        return repository.getExerciseById(id)
    }
}