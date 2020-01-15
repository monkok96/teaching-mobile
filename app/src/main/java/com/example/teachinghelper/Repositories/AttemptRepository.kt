package com.example.teachinghelper.Repositories

import com.example.teachinghelper.Dao.AttemptDao
import com.example.teachinghelper.Entities.Attempt
import com.example.teachinghelper.readmodel.DateElement
import java.sql.Date
import java.util.*

class AttemptRepository(private val attemptDao: AttemptDao) {
    val allAttempts: List<Attempt> = attemptDao.getAll()

    fun createNewAttempt(): Long {
        val currentTime = Calendar.getInstance().time;
        val dateSql = Date(currentTime.time)
        return this.attemptDao.insert( Attempt(null, dateSql) )
    }

    fun getAttemptDate(attemptId: Long) : DateElement {
        return this.attemptDao.getAttemptDate(attemptId)
    }
}