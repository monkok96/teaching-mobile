package com.example.teachinghelper.Helpers

import androidx.lifecycle.ViewModelProvider
import com.example.teachinghelper.Database.Entities.Answer
import com.example.teachinghelper.Database.Entities.AnswersHistory
import com.example.teachinghelper.View.ViewModels.AnswerHistoryViewModel
import com.example.teachinghelper.View.ViewModels.AttemptViewModel

class AnswersLogger {
    private val answers: MutableList<Answer> = mutableListOf()
    private var attemptModel: AttemptViewModel
    private var answerHistoryModel: AnswerHistoryViewModel

    constructor(provider: ViewModelProvider) {
        this.attemptModel = provider.get(AttemptViewModel::class.java)
        this.answerHistoryModel = provider.get(AnswerHistoryViewModel::class.java)
    }

    fun log(answer: Answer) {
        this.answers.add(answer)
    }

    fun answersCount(): Int {
        return this.answers.size
    }

    fun save(): Long {
        val attemptId = this.attemptModel.createNewAttempt()
        this.answers.forEach {
            if (it.id != null) {
                this.answerHistoryModel.insertAnswer(
                    AnswersHistory(
                        null,
                        attemptId,
                        it.questionId,
                        it.id
                    )
                )
            } else {
                throw Exception("Id is null")
            }
        }

        return attemptId
    }
}