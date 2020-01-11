package com.example.teachinghelper.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Question (
    @PrimaryKey(autoGenerate = true) val id: Int,
    val content: String
    )
