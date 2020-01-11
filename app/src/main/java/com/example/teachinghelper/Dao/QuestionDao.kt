package com.example.teachinghelper.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.teachinghelper.Entities.Question
import com.example.teachinghelper.readmodel.QuestionAllInfo

@Dao
interface QuestionDao {
    @Query("SELECT q.id, q.content, a.name as areaName FROM questions q JOIN areas a")
    fun getAll(): List<QuestionAllInfo>


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(question: Question)

    @Query("DELETE FROM questions")
    suspend fun deleteAll()
}