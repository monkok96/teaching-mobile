package com.example.teachinghelper.Database.Repositories

import com.example.teachinghelper.Database.Dao.AreaDao
import com.example.teachinghelper.Database.Entities.Area
import com.example.teachinghelper.Database.Models.AreaWithSubject

class AreaRepository(private val areaDao: AreaDao) {
    val allAreas: List<Area> = areaDao.getAll()
    fun getAreasBySubject(subjectId: Long) : List<Area> {
        return areaDao.getBySubject(subjectId)
    }

    fun getAreaWithSubjectById(areaId: Long) : AreaWithSubject {
        return areaDao.getAreaWithSubjectById(areaId)
    }


}