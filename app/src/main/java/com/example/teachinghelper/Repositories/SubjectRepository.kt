package com.example.teachinghelper.Repositories

import com.example.teachinghelper.Dao.SubjectDao
import com.example.teachinghelper.Entities.Area
import com.example.teachinghelper.Entities.Subject

class SubjectRepository(private val subjectDao: SubjectDao) {
    val allSubjects: List<Subject> = subjectDao.getAll()

    fun getByName(name: String) : Subject {
        return subjectDao.getByName(name)
    }

    fun getById(id: Long): Subject {
        return subjectDao.getById(id)
    }
}