package com.example.teachinghelper.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.teachinghelper.Entities.Question
import com.example.teachinghelper.readmodel.QuestionAllInfo

@Dao
interface QuestionDao {
    @Query("SELECT q.id, q.content, q.difficultyLevelId FROM questions q JOIN areas a")
    fun getAll(): List<QuestionAllInfo>

    @Query("SELECT q.id, q.content, q.difficultyLevelId FROM questions q JOIN areas a ON q.areaId=a.id JOIN subjects s ON a.subjectId=s.id WHERE a.id = :areaId")
    fun byAreaId(areaId: Int): List<QuestionAllInfo>

    @Query("SELECT q.id, q.content, q.difficultyLevelId FROM questions q JOIN areas a ON q.areaId=a.id JOIN subjects s ON a.subjectId=s.id WHERE a.id = :areaId and q.difficultyLevelId = :difficultyLevelId")
    fun byAreaIdWithDifficulty(areaId: Long, difficultyLevelId: Int): List<QuestionAllInfo>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(question: Question): Long

    @Query("DELETE FROM questions")
    suspend fun deleteAll()

}