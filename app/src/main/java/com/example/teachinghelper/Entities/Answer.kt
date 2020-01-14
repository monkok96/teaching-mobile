package com.example.teachinghelper.Entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "answers",
    foreignKeys = arrayOf(
        ForeignKey(entity = Question::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("questionId"),
            onDelete = ForeignKey.CASCADE)
    ))
data class Answer (
    @PrimaryKey(autoGenerate = true) val id: Long?,
    val content: String,
    val questionId: Long,
    val isCorrect: Boolean
)