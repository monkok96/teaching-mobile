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

    @Query("SELECT * FROM answershistory WHERE attemptId = :id")
    fun getByAttemptId(id: Long): List<AnswersHistory>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(attempt: AnswersHistory)

    @Query("DELETE FROM answershistory")
    suspend fun deleteAll()
}