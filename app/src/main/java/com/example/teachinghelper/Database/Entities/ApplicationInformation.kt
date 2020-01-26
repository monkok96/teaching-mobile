package com.example.teachinghelper.Database.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date


@Entity(tableName = "applicationInformation")
data class ApplicationInformation (
        @PrimaryKey(autoGenerate = true) val id: Long?,
        val name: String,
        var value: String
)