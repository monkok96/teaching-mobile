package com.example.teachinghelper.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.teachinghelper.Entities.Area

@Dao
interface AreaDao {
    @Query("SELECT * FROM areas")
    fun getAll(): List<Area>


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(area: Area)

    @Query("DELETE FROM areas")
    suspend fun deleteAll()
}