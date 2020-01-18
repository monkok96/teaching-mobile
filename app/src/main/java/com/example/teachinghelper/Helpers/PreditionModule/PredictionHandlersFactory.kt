package com.example.teachinghelper.Helpers.PreditionModule

import com.example.teachinghelper.Helpers.PreditionModule.PredictionsHandlers.BasicPrediction

class PredictionHandlersFactory {
    fun get(type: PredictionType): IPredictionHandler {
        when (type) {
            PredictionType.BASIC -> return BasicPrediction()
        }

        @Suppress("UNREACHABLE_CODE")
        throw Exception("Unknown prediction type: $type")
    }
}