package com.example.teachinghelper.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.teachinghelper.Entities.Answer

@Dao
interface AnswerDao {
    @Query("SELECT * FROM answers")
    fun getAll(): List<Answer>

    @Query("SELECT * FROM answers WHERE questionId = :questionId")
    fun getAnswersByQuestionId(questionId: Long): List<Answer>


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(answer: Answer): Long

    @Query("DELETE FROM answers")
    suspend fun deleteAll()

    @Query("SELECT * FROM answers WHERE questionId=:questionId AND isCorrect=1")
    fun getCorrectAnswerForQuestion(questionId: Long): Answer
}