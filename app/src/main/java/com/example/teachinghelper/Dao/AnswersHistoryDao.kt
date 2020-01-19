package com.example.teachinghelper.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.teachinghelper.Entities.Answer
import com.example.teachinghelper.Entities.AnswersHistory
import com.example.teachinghelper.readmodel.AttemptDetails
import com.example.teachinghelper.readmodel.Count
import com.example.teachinghelper.readmodel.PredictionAnswersHistory
import com.example.teachinghelper.readmodel.QuestionShortInfo

@Dao
interface AnswersHistoryDao {
    @Query("SELECT * FROM answershistory")
    fun getAll(): List<AnswersHistory>

    @Query("SELECT questions.id AS questionId, attempts.date AS answerDate, questions.difficultyLevelId AS difficultyLevel, answers.isCorrect AS isCorrect FROM answershistory LEFT JOIN attempts ON answershistory.attemptId = attempts.id LEFT JOIN questions ON answershistory.questionId = questions.id LEFT JOIN answers ON answershistory.answerId = answers.id WHERE questions.areaId = :areaId")
    fun getAllForPrediction(areaId: Long): List<PredictionAnswersHistory>

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


    @Query("SELECT q.id as questionId, q.content as questionContent, ah.id as answerHistoryId  FROM questions q JOIN answershistory ah ON ah.questionId=q.id WHERE ah.attemptId=:attemptId")
    fun getQuestionsWithinAttemp(attemptId: Long) : List<QuestionShortInfo>

    @Query("SELECT a.id, a.questionId, a.isCorrect, a.content FROM answershistory ah JOIN answers a ON ah.answerId=a.id WHERE ah.id=:answerHistoryId AND a.questionId=:questionId")
    fun getChosenAnswerForQuestionInAttempt(questionId: Long, answerHistoryId: Long) : Answer
}