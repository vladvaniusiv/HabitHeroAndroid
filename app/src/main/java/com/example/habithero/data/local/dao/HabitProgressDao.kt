package com.example.habithero.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.habithero.data.local.entity.HabitProgressEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HabitProgressDao {

    @Query("""
        SELECT * FROM habit_progress
        WHERE habitId = :habitId
        AND date BETWEEN :start AND :end
    """)
    fun getProgress(habitId: Int, start: String, end: String): Flow<List<HabitProgressEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProgress(progress: List<HabitProgressEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSingleProgress(progress: HabitProgressEntity)
}