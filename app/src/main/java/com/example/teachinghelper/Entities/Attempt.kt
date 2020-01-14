package com.example.teachinghelper.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date


@Entity(tableName = "attempts")
data class Attempt (
    @PrimaryKey(autoGenerate = true) val id: Int?,
    val date: Date
)