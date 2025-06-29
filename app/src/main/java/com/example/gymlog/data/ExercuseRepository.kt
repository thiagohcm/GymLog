package com.example.gymlog.data

import com.example.gymlog.data.dao.ExerciseDao
import com.example.gymlog.data.entity.Exercise
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ExerciseRepository @Inject constructor(
    private val exerciseDao: ExerciseDao
) {
    fun getAllExercises() = exerciseDao.getAllExercises()

    fun searchExercises(query: String) = exerciseDao.searchExercises(query)

    suspend fun insertExercise(exercise: Exercise) = exerciseDao.insert(exercise)

    suspend fun updateExercise(exercise: Exercise) = exerciseDao.update(exercise)

    suspend fun deleteExercise(exercise: Exercise) = exerciseDao.delete(exercise)

    suspend fun getExerciseById(id: Long) = exerciseDao.getExerciseById(id)

    suspend fun toggleFavorite(exercise: Exercise) {
        exerciseDao.updateFavorite(exercise.id, !exercise.isFavorite)
    }
}