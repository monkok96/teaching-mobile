package com.example.teachinghelper.Database.Repositories

import com.example.teachinghelper.Database.Dao.SubjectDao
import com.example.teachinghelper.Database.Entities.Subject

class SubjectRepository(private val subjectDao: SubjectDao) {
    val allSubjects: List<Subject> = subjectDao.getAll()

    fun getByName(name: String) : Subject {
        return subjectDao.getByName(name)
    }

    fun getById(id: Long): Subject {
        return subjectDao.getById(id)
    }
}