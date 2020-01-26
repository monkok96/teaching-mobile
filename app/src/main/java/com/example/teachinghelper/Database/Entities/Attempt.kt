package com.example.teachinghelper.Database.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date


@Entity(tableName = "attempts")
data class Attempt (
    @PrimaryKey(autoGenerate = true) val id: Long?,
    val date: Date
)