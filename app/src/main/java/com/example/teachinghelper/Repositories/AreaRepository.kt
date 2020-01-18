package com.example.teachinghelper.Repositories

import com.example.teachinghelper.Dao.AreaDao
import com.example.teachinghelper.Entities.Area
import com.example.teachinghelper.readmodel.AreaWithSubject

class AreaRepository(private val areaDao: AreaDao) {
    val allAreas: List<Area> = areaDao.getAll()
    fun getAreasBySubject(subjectId: Long) : List<Area> {
        return areaDao.getBySubject(subjectId)
    }

    fun getAreaWithSubjectById(areaId: Long) : AreaWithSubject {
        return areaDao.getAreaWithSubjectById(areaId)
    }


}