package com.example.teachinghelper.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.teachinghelper.Entities.Attempt

@Dao
interface AttemptDao {
    @Query("SELECT * FROM attempts")
    fun getAll(): List<Attempt>


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(attempt: Attempt)

    @Query("DELETE FROM attempts")
    suspend fun deleteAll()
}