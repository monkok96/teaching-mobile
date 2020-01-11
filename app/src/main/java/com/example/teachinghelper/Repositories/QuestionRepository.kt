package com.example.teachinghelper.Repositories

import androidx.lifecycle.LiveData
import com.example.teachinghelper.Dao.QuestionDao
import com.example.teachinghelper.Entities.Question

class QuestionRepository(private val questionDao: QuestionDao) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allQuestions: LiveData<List<Question>> = questionDao.getAll()

//    suspend fun insert(question: Question) {
//        questionDao.insert(question)
//    }
}