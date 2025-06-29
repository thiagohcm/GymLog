package com.example.gymlog.di

import android.content.Context
import androidx.room.Room
import com.example.gymlog.data.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "gym_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideExerciseDao(database: AppDatabase) = database.exerciseDao()
}