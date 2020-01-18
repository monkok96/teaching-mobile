package com.example.teachinghelper.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.teachinghelper.Database.AppDatabase
import com.example.teachinghelper.Entities.Area
import com.example.teachinghelper.Repositories.AreaRepository
import com.example.teachinghelper.readmodel.AreaWithSubject


class AreasViewModel (application: Application) : AndroidViewModel(application) {

    private val repository: AreaRepository
    val allAreas: List<Area>
    fun areasBySubject(subjectId: Long) : List<Area> {
        return repository.getAreasBySubject(subjectId)
    }

    fun getAreaWithSubjectById(areaId: Long) : AreaWithSubject {
        return repository.getAreaWithSubjectById(areaId)
    }

    init {
        val areaDao = AppDatabase.getDatabase(application, viewModelScope).areaDao()
        repository = AreaRepository(areaDao)
        allAreas = repository.allAreas
    }
}
