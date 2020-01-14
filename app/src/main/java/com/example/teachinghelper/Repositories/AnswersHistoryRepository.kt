package com.example.teachinghelper.Repositories

import com.example.teachinghelper.Dao.AnswerDao
import com.example.teachinghelper.Dao.AnswersHistoryDao
import com.example.teachinghelper.Dao.AreaDao
import com.example.teachinghelper.Dao.AttemptDao
import com.example.teachinghelper.Entities.Answer
import com.example.teachinghelper.Entities.AnswersHistory
import com.example.teachinghelper.Entities.Area
import com.example.teachinghelper.Entities.Attempt

class AnswersHistoryRepository(private val answersHistoryDao: AnswersHistoryDao) {
    val allAnswersHistory: List<AnswersHistory> = answersHistoryDao.getAll()

    fun getByAttemptId(id: Long): List<AnswersHistory> {
        return this.answersHistoryDao.getByAttemptId(id)
    }

    fun insertAnswer(answer: AnswersHistory) {
        this.answersHistoryDao.insert(answer)
    }
}