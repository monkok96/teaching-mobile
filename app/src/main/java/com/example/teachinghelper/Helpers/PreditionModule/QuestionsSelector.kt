package com.example.teachinghelper.Helpers.PreditionModule

import androidx.lifecycle.ViewModelProvider
import com.example.teachinghelper.ViewModels.AnswerHistoryViewModel
import com.example.teachinghelper.ViewModels.QuestionViewModel
import com.example.teachinghelper.readmodel.QuestionAllInfo

class QuestionsSelector {
    private val factory: PredictionHandlersFactory
    private val questionModel: QuestionViewModel
    private val answerHistoryModel: AnswerHistoryViewModel

    constructor(provider: ViewModelProvider, factory: PredictionHandlersFactory) {
        this.factory = factory
        this.questionModel = provider.get(QuestionViewModel::class.java)
        this.answerHistoryModel = provider.get(AnswerHistoryViewModel::class.java)
    }

    fun get(type: PredictionType, areaId: Long, count: Int): List<QuestionAllInfo> {
        val handler = this.factory.get(type)
        val questionsToGet = handler.get(this.answerHistoryModel.getAllForPrediction(areaId), count)

        val questions: MutableList<QuestionAllInfo> = mutableListOf()

        questionsToGet.forEach { (difficultyLevel, questionsCount) ->
            if (questionsCount != 0) {
                val selectedQuestions = this.questionModel.byAreaIdWithDifficulty(areaId, difficultyLevel).shuffled().take(questionsCount)
                questions.addAll(selectedQuestions)
            }
        }

        return questions.toList()
    }
}