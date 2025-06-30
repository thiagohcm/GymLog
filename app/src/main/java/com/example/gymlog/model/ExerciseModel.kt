package com.example.gymlog.model

import com.example.gymlog.data.entity.Exercise

data class ExerciseModel(
    val id: Long = 0L,
    val name: String,
    val category: String,
    val series: Int,
    val repetitions: Int,
    val notes: String? = null,
    val isFavorite: Boolean = false
) {
    fun toEntity() = Exercise(
        id = id,
        name = name,
        category = category,
        series = series,
        repetitions = repetitions,
        notes = notes,
        isFavorite = isFavorite
    )
}

fun Exercise.toModel() = ExerciseModel(
    id = id,
    name = name,
    category = category,
    series = series,
    repetitions = repetitions,
    notes = notes,
    isFavorite = isFavorite
)