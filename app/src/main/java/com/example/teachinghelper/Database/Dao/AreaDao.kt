package com.example.teachinghelper.Database.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.teachinghelper.Database.Entities.Area
import com.example.teachinghelper.Database.Models.AreaWithSubject

@Dao
interface AreaDao {
    @Query("SELECT * FROM areas")
    fun getAll(): List<Area>


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(area: Area): Long

    @Query("DELETE FROM areas")
    suspend fun deleteAll()

    @Query ("SELECT a.id, a.name, a.subjectId FROM areas a JOIN subjects s on a.subjectId = s.id WHERE s.id=:subjectId")
    fun getBySubject(subjectId: Long): List<Area>

    @Query ("SELECT a.name as areaName, s.name as subjectName FROM areas a JOIN subjects s on a.subjectId = s.id WHERE a.id=:areaId")
    fun getAreaWithSubjectById(areaId: Long): AreaWithSubject
}