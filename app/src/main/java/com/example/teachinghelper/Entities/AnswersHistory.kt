package com.example.teachinghelper.Entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "answershistory",
    foreignKeys = arrayOf(
        ForeignKey(entity = Attempt::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("attemptId"),
            onDelete = ForeignKey.CASCADE),
        ForeignKey(entity = Question::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("questionId"),
            onDelete = ForeignKey.CASCADE),
        ForeignKey(entity = Answer::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("answerId"),
            onDelete = ForeignKey.CASCADE)
    ))
data class AnswersHistory (
    @PrimaryKey(autoGenerate = true) val id: Int,
    val attemptId: Int,
    val questionId: Int,
    val answerId: Int
)
