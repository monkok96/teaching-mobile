package com.example.teachinghelper.Repositories

import com.example.teachinghelper.Dao.AnswerDao
import com.example.teachinghelper.Dao.AreaDao
import com.example.teachinghelper.Entities.Answer
import com.example.teachinghelper.Entities.Area

class AnswerRepository(private val answerDao: AnswerDao) {
    val allAnswers: List<Answer> = answerDao.getAll()

    fun getAnswersByQuestionId(questionId: Long) : List<Answer> {
        return answerDao.getAnswersByQuestionId(questionId)
    }

    fun getCorrectAnswerForQuestion(questionId: Long): Answer {
        return answerDao.getCorrectAnswerForQuestion(questionId)
    }

}