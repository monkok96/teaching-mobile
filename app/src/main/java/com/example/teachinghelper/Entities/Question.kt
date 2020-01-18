package com.example.teachinghelper.Entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "questions",
    foreignKeys = arrayOf(
        ForeignKey(
            entity = Area::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("areaId"),
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = DifficultyLevel::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("difficultyLevelId"),
            onDelete = ForeignKey.CASCADE
        )
    ))
data class Question (
    @PrimaryKey(autoGenerate = true) val id: Long?,
    val areaId: Long,
    val content: String,
    val difficultyLevelId: Long
    )
