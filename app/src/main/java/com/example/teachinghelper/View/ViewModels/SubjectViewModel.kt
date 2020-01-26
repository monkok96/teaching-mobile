package com.example.teachinghelper.View.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.teachinghelper.Database.AppDatabase
import com.example.teachinghelper.Database.Entities.Subject
import com.example.teachinghelper.Database.Repositories.SubjectRepository

class SubjectViewModel (application: Application) : AndroidViewModel(application) {

    private val repository: SubjectRepository
    val allSubjects: List<Subject>

    fun subjectByName(name: String) : Subject {
        return repository.getByName(name)
    }

    fun getSubjectById(id: Long) : Subject {
        return repository.getById(id)
    }

    init {
        val subjectDao = AppDatabase.getDatabase(application, viewModelScope).subjectDao()
        repository = SubjectRepository(subjectDao)
        allSubjects = repository.allSubjects
    }
}
