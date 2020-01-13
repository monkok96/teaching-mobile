package com.example.teachinghelper.Repositories

import com.example.teachinghelper.Dao.AnswerDao
import com.example.teachinghelper.Dao.AreaDao
import com.example.teachinghelper.Entities.Answer
import com.example.teachinghelper.Entities.Area

class AnswerRepository(private val answerDao: AnswerDao) {
    val allAnswers: List<Answer> = answerDao.getAll()

    fun getAnswersByQuestionId(questionId: Int) : List<Answer> {
        return answerDao.getAnswersByQuestionId(questionId)
    }

}