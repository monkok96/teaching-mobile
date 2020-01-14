package com.example.teachinghelper.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.teachinghelper.Entities.AnswersHistory
import com.example.teachinghelper.readmodel.Count

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

    @Query("SELECT COUNT(*) as value FROM answershistory where attemptId=:attemptId GROUP BY attemptId")
    fun getQuestionsCountInAttempt(attemptId: Long): Count?

    @Query("SELECT COUNT(*) as value FROM answershistory ah JOIN answers a ON ah.answerId=a.id where attemptId=:attemptId AND a.isCorrect=1 GROUP BY attemptId")
    fun getCorrectAnswersCountInAttempt(attemptId: Long): Count?
}