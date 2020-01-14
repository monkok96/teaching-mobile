package com.example.teachinghelper.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.teachinghelper.Database.AppDatabase
import com.example.teachinghelper.Entities.Answer
import com.example.teachinghelper.Entities.AnswersHistory
import com.example.teachinghelper.Entities.Area
import com.example.teachinghelper.Repositories.AnswerRepository
import com.example.teachinghelper.Repositories.AnswersHistoryRepository
import com.example.teachinghelper.Repositories.AreaRepository
import com.example.teachinghelper.readmodel.Count


class AnswerHistoryViewModel (application: Application) : AndroidViewModel(application) {

    private val repository: AnswersHistoryRepository

    fun insertAnswer(answer: AnswersHistory) {
        this.repository.insertAnswer(answer)
    }

    fun getByAttemptId(id: Long): List<AnswersHistory> {
        return this.repository.getByAttemptId(id)
    }

    fun getQuestionsCountInAttempt(attemptId: Long): Count {
        return this.repository.getQuestionsCountInAttempt(attemptId)
    }

    fun getCorrectAnswersCountInAttempt(attemptId: Long): Count {
        return this.repository.getCorrectAnswersCountInAttempt(attemptId)
    }

    init {
        repository = AnswersHistoryRepository(AppDatabase.getDatabase(application, viewModelScope).answersHistoryDao())
    }
}
