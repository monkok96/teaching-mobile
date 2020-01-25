package com.example.teachinghelper.View.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.teachinghelper.Database.AppDatabase
import com.example.teachinghelper.Database.Repositories.AttemptRepository
import com.example.teachinghelper.Database.Models.DateElement

class AttemptViewModel (application: Application) : AndroidViewModel(application) {

    private val repository: AttemptRepository

    init {
        repository = AttemptRepository(AppDatabase.getDatabase(application, viewModelScope).attemptDao())
    }

    fun createNewAttempt(): Long {
        return this.repository.createNewAttempt()
    }

    fun getAttemptDate(attemptId: Long) : DateElement {
        return this.repository.getAttemptDate(attemptId)
    }
}
