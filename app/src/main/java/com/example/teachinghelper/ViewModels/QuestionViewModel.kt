package com.example.teachinghelper.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.teachinghelper.Database.AppDatabase
import com.example.teachinghelper.Repositories.QuestionRepository
import androidx.lifecycle.viewModelScope
import com.example.teachinghelper.Entities.Subject
import com.example.teachinghelper.readmodel.QuestionAllInfo

class QuestionViewModel (application: Application) : AndroidViewModel(application) {

    // The ViewModel maintains a reference to the repository to get data.
    private val repository: QuestionRepository

    init {
        val questionsDao = AppDatabase.getDatabase(application, viewModelScope).questionDao()
        repository = QuestionRepository(questionsDao)
    }

    fun byAreaId(areaId: Int) : List<QuestionAllInfo> {
        return repository.byAreaId(areaId);
    }

    fun byAreaIdWithDifficulty(areaId: Long, difficultyLevelId: Int): List<QuestionAllInfo> {
        return this.repository.byAreaIdWithDifficulty(areaId, difficultyLevelId)
    }
    /**
     * The implementation of insert() in the database is completely hidden from the UI.
     * Room ensures that you're not doing any long running operations on
     * the main thread, blocking the UI, so we don't need to handle changing Dispatchers.
     * ViewModels have a coroutine scope based on their lifecycle called
     * viewModelScope which we can use here.
     */
//    fun insert(word: Word) = viewModelScope.launch {
//        repository.insert(word)
//    }
}