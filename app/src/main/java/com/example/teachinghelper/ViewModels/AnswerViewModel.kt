package com.example.teachinghelper.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.teachinghelper.Database.AppDatabase
import com.example.teachinghelper.Entities.Answer
import com.example.teachinghelper.Entities.Area
import com.example.teachinghelper.Repositories.AnswerRepository
import com.example.teachinghelper.Repositories.AreaRepository


class AnswerViewModel (application: Application) : AndroidViewModel(application) {

    private val repository: AnswerRepository

    fun getAnswersByQuestionId(questionId: Int) : List<Answer> {
        return repository.getAnswersByQuestionId(questionId)
    }

    init {
        repository = AnswerRepository(AppDatabase.getDatabase(application, viewModelScope).answerDao())
    }
}