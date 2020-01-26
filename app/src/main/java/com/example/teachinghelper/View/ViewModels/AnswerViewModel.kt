package com.example.teachinghelper.View.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.teachinghelper.Database.AppDatabase
import com.example.teachinghelper.Database.Entities.Answer
import com.example.teachinghelper.Database.Repositories.AnswerRepository


class AnswerViewModel (application: Application) : AndroidViewModel(application) {

    private val repository: AnswerRepository

    fun getAnswersByQuestionId(questionId: Long) : List<Answer> {
        return repository.getAnswersByQuestionId(questionId)
    }

    fun getCorrectAnswerForQuestion(questionId: Long): Answer {
        return repository.getCorrectAnswerForQuestion(questionId)
    }

    init {
        repository = AnswerRepository(AppDatabase.getDatabase(application, viewModelScope).answerDao())
    }
}
