package com.example.teachinghelper.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.teachinghelper.Database.AppDatabase
import com.example.teachinghelper.Entities.Subject
import com.example.teachinghelper.Repositories.AttemptRepository
import com.example.teachinghelper.Repositories.SubjectRepository

class AttemptViewModel (application: Application) : AndroidViewModel(application) {

    private val repository: AttemptRepository

    init {
        repository = AttemptRepository(AppDatabase.getDatabase(application, viewModelScope).attemptDao())
    }

    fun createNewAttempt(): Long {
        return this.repository.createNewAttempt()
    }
}
