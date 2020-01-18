package com.example.teachinghelper.Dao

import androidx.room.*
import com.example.teachinghelper.Entities.ApplicationInformation
import com.example.teachinghelper.Entities.DifficultyLevel

@Dao
interface ApplicationInformationDao {
    @Query("SELECT * FROM applicationInformation")
    fun getAll(): List<ApplicationInformation>

    @Query("SELECT * FROM applicationInformation WHERE name = :name")
    fun getByPropertyName(name: String): ApplicationInformation

    @Update
    suspend fun update(information: ApplicationInformation)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(information: ApplicationInformation)

    @Query("DELETE FROM applicationInformation")
    suspend fun deleteAll()
}