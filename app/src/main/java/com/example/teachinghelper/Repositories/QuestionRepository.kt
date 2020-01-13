package com.example.teachinghelper.Repositories

import com.example.teachinghelper.Dao.QuestionDao
import com.example.teachinghelper.readmodel.QuestionAllInfo

class QuestionRepository(private val questionDao: QuestionDao) {

    val allQuestionsWithAreas: List<QuestionAllInfo> = questionDao.getAll()

    fun byAreaId(areaId: Int) : List<QuestionAllInfo> {
        return questionDao.byAreaId(areaId)
    }
}