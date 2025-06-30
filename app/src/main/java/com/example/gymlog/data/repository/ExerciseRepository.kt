package com.example.gymlog.data.repository

import com.example.gymlog.data.dao.ExerciseDao
import com.example.gymlog.model.ExerciseModel
import com.example.gymlog.model.toModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ExerciseRepository @Inject constructor(
    private val exerciseDao: ExerciseDao
) {
    fun getAllExercises(): Flow<List<ExerciseModel>> =
        exerciseDao.getAllExercises().map { list -> list.map { it.toModel() } }

    fun searchExercises(query: String): Flow<List<ExerciseModel>> =
        exerciseDao.searchExercises(query).map { list -> list.map { it.toModel() } }

    suspend fun insertExercise(exercise: ExerciseModel) =
        exerciseDao.insert(exercise.toEntity())

    suspend fun updateExercise(exercise: ExerciseModel) =
        exerciseDao.update(exercise.toEntity())

    suspend fun deleteExercise(exercise: ExerciseModel) =
        exerciseDao.delete(exercise.toEntity())

    suspend fun getExerciseById(id: Long): ExerciseModel? =
        exerciseDao.getExerciseById(id)?.toModel()

    suspend fun toggleFavorite(exercise: ExerciseModel) {
        exerciseDao.updateFavorite(exercise.id, !exercise.isFavorite)
    }
}