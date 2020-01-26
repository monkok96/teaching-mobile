package com.example.teachinghelper.Database.Repositories

import com.example.teachinghelper.Database.Dao.AnswerDao
import com.example.teachinghelper.Database.Entities.Answer

class AnswerRepository(private val answerDao: AnswerDao) {
    val allAnswers: List<Answer> = answerDao.getAll()

    fun getAnswersByQuestionId(questionId: Long) : List<Answer> {
        return answerDao.getAnswersByQuestionId(questionId)
    }

    fun getCorrectAnswerForQuestion(questionId: Long): Answer {
        return answerDao.getCorrectAnswerForQuestion(questionId)
    }

}