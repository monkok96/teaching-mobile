package com.example.teachinghelper.readmodel

class AttemptDetails (
    val questionId: Long,
    val questionContent: String,
    val chosenAnswerId: Long,
    val chosenAnswerContent: String,
    val correctAnswerId: Long,
    val correctAnswerContent: String
) {
    fun isCorrect(): Boolean {
        return chosenAnswerId == correctAnswerId
    }
}