package com.example.teachinghelper.Helpers.PreditionModule.PredictionsHandlers

import com.example.teachinghelper.Entities.AnswersHistory
import com.example.teachinghelper.Helpers.PreditionModule.IPredictionHandler

class BasicPrediction: IPredictionHandler {
    override fun get(history: List<AnswersHistory>, count: Int): Map<Int, Int> {
        return mapOf(1 to 0, 2 to 2, 3 to 1)
    }
}