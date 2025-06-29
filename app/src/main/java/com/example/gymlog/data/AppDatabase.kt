package com.example.gymlog.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.gymlog.data.dao.ExerciseDao
import com.example.gymlog.data.entity.Exercise

@Database(entities = [Exercise::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun exerciseDao(): ExerciseDao
}