package com.example.gymlog.data.dao

    import androidx.room.Dao
    import androidx.room.Delete
    import androidx.room.Insert
    import androidx.room.Query
    import androidx.room.Update
    import com.example.gymlog.data.entity.Exercise
    import kotlinx.coroutines.flow.Flow

    @Dao
    interface ExerciseDao {
        @Insert
        suspend fun insert(exercise: Exercise): Long

        @Update
        suspend fun update(exercise: Exercise)

        @Delete
        suspend fun delete(exercise: Exercise)

        @Query("""
            SELECT * FROM exercise
            ORDER BY isFavorite DESC, name ASC
        """)
        fun getAllExercises(): Flow<List<Exercise>>

        @Query("""
            SELECT * FROM exercise
            WHERE (name LIKE '%' || :searchQuery || '%'
            OR category LIKE '%' || :searchQuery || '%'
            OR notes LIKE '%' || :searchQuery || '%')
            ORDER BY isFavorite DESC, name ASC
        """)
        fun searchExercises(searchQuery: String): Flow<List<Exercise>>

        @Query("SELECT * FROM exercise WHERE id = :id")
        suspend fun getExerciseById(id: Long): Exercise?

        @Query("UPDATE exercise SET isFavorite = :isFavorite WHERE id = :id")
        suspend fun updateFavorite(id: Long, isFavorite: Boolean)
    }