package com.example.teachinghelper.Helpers.PreditionModule

import com.example.teachinghelper.Entities.AnswersHistory
import com.example.teachinghelper.Entities.Question
import com.example.teachinghelper.readmodel.PredictionAnswersHistory

interface IPredictionHandler {
    fun get(history: List<PredictionAnswersHistory>, count: Int): Map<Int, Int>
}