package com.example.teachinghelper.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.teachinghelper.Database.AppDatabase
import com.example.teachinghelper.Entities.Area
import com.example.teachinghelper.Repositories.AreaRepository


class AreasViewModel (application: Application) : AndroidViewModel(application) {

    private val repository: AreaRepository
    val allAreas: List<Area>

    init {
        val areaDao = AppDatabase.getDatabase(application, viewModelScope).areaDao()
        repository = AreaRepository(areaDao)
        allAreas = repository.allAreas
    }
}
