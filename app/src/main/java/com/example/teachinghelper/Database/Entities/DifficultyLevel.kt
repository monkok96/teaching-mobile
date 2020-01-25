package com.example.teachinghelper.Database.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "difficultyLevels")
data class DifficultyLevel (
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val level: Int //some numbers, like from 1 to 5 where 1 is the easies or something
)