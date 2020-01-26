package com.example.teachinghelper.Database.Models

class AreaWithSubject (
    val areaName: String,
    val subjectName: String
) {
    fun getString(): String{
        return "$subjectName - $areaName"
    }
}