package com.example.teachinghelper.Database.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "subjects")

data class Subject (
    @PrimaryKey(autoGenerate = true) val id: Long?,
    val name: String
)