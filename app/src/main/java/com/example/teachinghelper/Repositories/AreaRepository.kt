package com.example.teachinghelper.Repositories

import com.example.teachinghelper.Dao.AreaDao
import com.example.teachinghelper.Entities.Area

class AreaRepository(private val areaDao: AreaDao) {
    val allAreas: List<Area> = areaDao.getAll()

}