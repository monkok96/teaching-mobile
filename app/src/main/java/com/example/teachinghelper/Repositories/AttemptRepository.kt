package com.example.teachinghelper.Repositories

import com.example.teachinghelper.Dao.AttemptDao
import com.example.teachinghelper.Entities.Attempt
import java.sql.Date
import java.util.*

class AttemptRepository(private val attemptDao: AttemptDao) {
    val allAttempts: List<Attempt> = attemptDao.getAll()

    fun createNewAttempt(): Long {
        val currentTime = Calendar.getInstance().time;
        val dateSql = Date(currentTime.time)
        return this.attemptDao.insert( Attempt(null, dateSql) )
    }
}