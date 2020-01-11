package com.example.teachinghelper.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.teachinghelper.Entities.Question

@Dao
interface QuestionDao {
    @Query("SELECT * FROM Question")
    fun getAll(): LiveData<List<Question>>


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(question: Question)

    @Query("DELETE FROM Question")
    suspend fun deleteAll()
}