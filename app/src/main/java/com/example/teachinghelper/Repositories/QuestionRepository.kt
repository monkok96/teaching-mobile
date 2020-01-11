package com.example.teachinghelper.Repositories

import androidx.lifecycle.LiveData
import com.example.teachinghelper.Dao.QuestionDao
import com.example.teachinghelper.Entities.Question
import com.example.teachinghelper.readmodel.QuestionAllInfo

class QuestionRepository(private val questionDao: QuestionDao) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allQuestionsWithAreas: List<QuestionAllInfo> = questionDao.getAll()

//    suspend fun insert(question: Question) {
//        questionDao.insert(question)
//    }
}