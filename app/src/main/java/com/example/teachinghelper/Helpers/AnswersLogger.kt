package com.example.teachinghelper.Helpers

import com.example.teachinghelper.Entities.Answer

class AnswersLogger {
    private val answers: MutableList<Answer> = mutableListOf()

    fun log(answer: Answer) {
        this.answers.add(answer)
    }

    fun answersCount(): Int {
        return this.answers.size
    }
}