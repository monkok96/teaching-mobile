package com.example.teachinghelper.Database.Models

import java.sql.Date

class PredictionAnswersHistory (
    val questionId: Long,
    val answerDate: Date,
    val difficultyLevel: Long,
    val isCorrect: Boolean
)