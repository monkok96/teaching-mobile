package com.example.teachinghelper.Database.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.teachinghelper.Database.Entities.Attempt
import com.example.teachinghelper.Database.Models.DateElement

@Dao
interface AttemptDao {
    @Query("SELECT * FROM attempts")
    fun getAll(): List<Attempt>


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(attempt: Attempt): Long

    @Query("DELETE FROM attempts")
    suspend fun deleteAll()


    @Query("SELECT date as value from attempts WHERE id=:attemptId")
    fun getAttemptDate(attemptId: Long) : DateElement

}