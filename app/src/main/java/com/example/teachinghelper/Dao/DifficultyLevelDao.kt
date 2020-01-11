package com.example.teachinghelper.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.teachinghelper.Entities.DifficultyLevel

@Dao
interface DifficultyLevelDao {
    @Query("SELECT * FROM difficultyLevels")
    fun getAll(): List<DifficultyLevel>


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(level: DifficultyLevel)

    @Query("DELETE FROM difficultyLevels")
    suspend fun deleteAll()
}