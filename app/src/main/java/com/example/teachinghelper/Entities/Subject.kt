package com.example.teachinghelper.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "subjects")

data class Subject (
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String
)