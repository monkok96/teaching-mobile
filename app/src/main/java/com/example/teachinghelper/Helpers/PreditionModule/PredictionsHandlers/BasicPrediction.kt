package com.example.teachinghelper.Helpers.PreditionModule.PredictionsHandlers

import com.example.teachinghelper.Helpers.PreditionModule.IPredictionHandler
import com.example.teachinghelper.Database.Models.PredictionAnswersHistory
import kotlin.random.Random

class BasicPrediction: IPredictionHandler {
    override fun get(history: List<PredictionAnswersHistory>, count: Int): Map<Int, Int> {
        var mutableCount = count
        val easy = Random.nextInt(0, mutableCount)
        val medium = Random.nextInt(0, mutableCount - easy)
        val hard = mutableCount - easy - medium
        return mapOf(1 to easy, 2 to medium, 3 to hard)
    }
}