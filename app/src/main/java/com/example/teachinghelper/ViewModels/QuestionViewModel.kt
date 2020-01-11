package com.example.teachinghelper.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.teachinghelper.Database.AppDatabase
import com.example.teachinghelper.Repositories.QuestionRepository
import androidx.lifecycle.viewModelScope
import com.example.teachinghelper.readmodel.QuestionAllInfo

class QuestionViewModel (application: Application) : AndroidViewModel(application) {

    // The ViewModel maintains a reference to the repository to get data.
    private val repository: QuestionRepository
    // LiveData gives us updated words when they change.
    val allQuestions: List<QuestionAllInfo>

    init {
        // Gets reference to WordDao from WordRoomDatabase to construct
        // the correct WordRepository.
        val questionsDao = AppDatabase.getDatabase(application, viewModelScope).questionDao()
        repository = QuestionRepository(questionsDao)
        allQuestions = repository.allQuestionsWithAreas
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