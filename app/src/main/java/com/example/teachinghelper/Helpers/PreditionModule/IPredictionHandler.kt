package com.example.teachinghelper.Helpers.PreditionModule

import com.example.teachinghelper.Entities.AnswersHistory
import com.example.teachinghelper.Entities.Question

interface IPredictionHandler {
    fun get(history: List<AnswersHistory>, count: Int): Map<Int, Int>
}