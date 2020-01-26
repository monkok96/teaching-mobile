package com.example.teachinghelper.Database.Entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "areas",
    foreignKeys = arrayOf(
    ForeignKey(entity = Subject::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("subjectId"),
        onDelete = ForeignKey.CASCADE)
    ))
data class Area (
    @PrimaryKey(autoGenerate = true) val id: Long?,
    val name: String,
    val subjectId: Long
)
