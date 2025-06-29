package com.example.gymlog.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

// This code defines an Exercise data class that represents an exercise entity in a Room database.
@Entity(tableName = "exercise")
data class Exercise(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val name: String,
    val category: String,
    val series: Int,
    val repetitions: Int,
    val notes: String? = null,
    val isFavorite: Boolean = false
)