package com.example.teachinghelper.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.teachinghelper.Database.AppDatabase
import com.example.teachinghelper.Entities.Subject
import com.example.teachinghelper.Repositories.SubjectRepository

class SubjectViewModel (application: Application) : AndroidViewModel(application) {

    private val repository: SubjectRepository
    val allSubjects: List<Subject>

    fun subjectByName(name: String) : Subject {
        return repository.getByName(name)
    }

    init {
        val subjectDao = AppDatabase.getDatabase(application, viewModelScope).subjectDao()
        repository = SubjectRepository(subjectDao)
        allSubjects = repository.allSubjects
    }
}
