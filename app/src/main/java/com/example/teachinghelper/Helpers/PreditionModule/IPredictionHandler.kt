package com.example.teachinghelper.Helpers.PreditionModule

import com.example.teachinghelper.Database.Models.PredictionAnswersHistory

interface IPredictionHandler {
    fun get(history: List<PredictionAnswersHistory>, count: Int): Map<Int, Int>
}