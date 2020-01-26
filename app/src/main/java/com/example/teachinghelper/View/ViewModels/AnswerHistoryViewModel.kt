package com.example.teachinghelper.View.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.teachinghelper.Database.AppDatabase
import com.example.teachinghelper.Database.Entities.Answer
import com.example.teachinghelper.Database.Entities.AnswersHistory
import com.example.teachinghelper.Database.Repositories.AnswersHistoryRepository
import com.example.teachinghelper.Database.Models.Count
import com.example.teachinghelper.Database.Models.PredictionAnswersHistory
import com.example.teachinghelper.Database.Models.QuestionShortInfo


class AnswerHistoryViewModel (application: Application) : AndroidViewModel(application) {

    private val repository: AnswersHistoryRepository

    fun insertAnswer(answer: AnswersHistory) {
        this.repository.insertAnswer(answer)
    }

    fun getByAttemptId(id: Long): List<AnswersHistory> {
        return this.repository.getByAttemptId(id)
    }

    fun getAllForPrediction(areaId: Long): List<PredictionAnswersHistory> {
        return this.repository.getAllForPrediction(areaId)
    }

    fun getQuestionsCountInAttempt(attemptId: Long): Count {
        return this.repository.getQuestionsCountInAttempt(attemptId)
    }

    fun getCorrectAnswersCountInAttempt(attemptId: Long): Count {
        return this.repository.getCorrectAnswersCountInAttempt(attemptId)
    }

    fun getQuestionsWithinAttemp(attemptId: Long) : List<QuestionShortInfo> {
        return this.repository.getQuestionsWithinAttempt(attemptId)
    }

    fun getChosenAnswerForQuestion(questionId: Long, answerHistoryId: Long) : Answer {
        return this.repository. getChosenAnswerForQuestionInAttempt(questionId, answerHistoryId)
    }

    fun getAll(): List<AnswersHistory> {
        return this.repository.allAnswersHistory
    }

    init {
        repository = AnswersHistoryRepository(AppDatabase.getDatabase(application, viewModelScope).answersHistoryDao())
    }
}
