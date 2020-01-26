package com.example.teachinghelper.Database.Repositories

import com.example.teachinghelper.Database.Dao.AnswersHistoryDao
import com.example.teachinghelper.Database.Entities.Answer
import com.example.teachinghelper.Database.Entities.AnswersHistory
import com.example.teachinghelper.Database.Models.Count
import com.example.teachinghelper.Database.Models.PredictionAnswersHistory
import com.example.teachinghelper.Database.Models.QuestionShortInfo

class AnswersHistoryRepository(private val answersHistoryDao: AnswersHistoryDao) {
    val allAnswersHistory: List<AnswersHistory> = answersHistoryDao.getAll()

    fun getByAttemptId(id: Long): List<AnswersHistory> {
        return this.answersHistoryDao.getByAttemptId(id)
    }

    fun getAllForPrediction(areaId: Long): List<PredictionAnswersHistory> {
        return this.answersHistoryDao.getAllForPrediction(areaId)
    }

    fun insertAnswer(answer: AnswersHistory) {
        this.answersHistoryDao.insert(answer)
    }

    fun getQuestionsCountInAttempt(attemptId: Long): Count {
        return this.answersHistoryDao.getQuestionsCountInAttempt(attemptId) ?: Count(0)
    }

    fun getCorrectAnswersCountInAttempt(attemptId: Long): Count {
        return this.answersHistoryDao.getCorrectAnswersCountInAttempt(attemptId)?: Count(0)
    }

    fun getQuestionsWithinAttempt(attemptId: Long) : List<QuestionShortInfo> {
        return this.answersHistoryDao.getQuestionsWithinAttemp(attemptId)
    }

    fun  getChosenAnswerForQuestionInAttempt(questionId: Long,  answerHistoryId: Long) : Answer {
        return this.answersHistoryDao. getChosenAnswerForQuestionInAttempt(questionId, answerHistoryId)
    }


}