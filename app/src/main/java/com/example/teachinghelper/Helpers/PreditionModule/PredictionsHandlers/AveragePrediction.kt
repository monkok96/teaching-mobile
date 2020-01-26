package com.example.teachinghelper.Helpers.PreditionModule.PredictionsHandlers

import com.example.teachinghelper.Helpers.PreditionModule.IPredictionHandler
import com.example.teachinghelper.Database.Models.PredictionAnswersHistory

class AveragePrediction: IPredictionHandler {
    override fun get(history: List<PredictionAnswersHistory>, count: Int): Map<Int, Int> {
        val answers = mutableMapOf(1 to 0, 2 to 0, 3 to 0)

        var correctAnswersAverage = history.filter{ it.isCorrect }.map { it.difficultyLevel }.average()

        if (correctAnswersAverage.isNaN()) {
            correctAnswersAverage = 1.0
        }

        var higherQuestionsPercentage = correctAnswersAverage - correctAnswersAverage.toInt() + 0.5
        if( higherQuestionsPercentage > 3){
            higherQuestionsPercentage = 3.0
        }
        val higherQuestions = (count * higherQuestionsPercentage).toInt()

        val tenPercentOfRemainingQuestions = ((count - higherQuestions) * 0.1)

        var highestLevelQuestions: Int

        if (tenPercentOfRemainingQuestions.toInt() < 1) {
            highestLevelQuestions = 1
        } else {
            highestLevelQuestions = tenPercentOfRemainingQuestions.toInt()
        }

        answers[3] = answers[3]!!.toInt() + highestLevelQuestions
        answers[correctAnswersAverage.toInt() + 1] = answers[correctAnswersAverage.toInt() + 1]!! + higherQuestions
        answers[correctAnswersAverage.toInt()] = answers[correctAnswersAverage.toInt()]!!.toInt() + count - answers[correctAnswersAverage.toInt()]!! - answers[correctAnswersAverage.toInt() + 1]!!

        return answers
    }
}