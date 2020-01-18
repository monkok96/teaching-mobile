package com.example.teachinghelper.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.teachinghelper.Entities.Subject

@Dao
interface SubjectDao {
    @Query("SELECT * FROM subjects")
    fun getAll(): List<Subject>


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(subject: Subject): Long

    @Query("DELETE FROM subjects")
    suspend fun deleteAll()

    @Query ("SELECT * FROM subjects s WHERE  s.name=:name")
    fun getByName(name: String): Subject

    @Query("SELECT * FROM subjects s WHERE s.id=:id")
    fun getById(id: Long): Subject
}