package com.example.teachinghelper.readmodel

class AreaWithSubject (
    val areaName: String,
    val subjectName: String
) {
    fun getString(): String{
        return "$subjectName - $areaName"
    }
}