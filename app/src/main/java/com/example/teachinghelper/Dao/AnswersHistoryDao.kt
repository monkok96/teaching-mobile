package com.example.teachinghelper.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.teachinghelper.Entities.AnswersHistory

@Dao
interface AnswersHistoryDao {
    @Query("SELECT * FROM answershistory")
    fun getAll(): List<AnswersHistory>


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(attempt: AnswersHistory)

    @Query("DELETE FROM answershistory")
    suspend fun deleteAll()
}