package com.example.teachinghelper.Database

import androidx.room.TypeConverter
import java.sql.Date


class Converters {
    @TypeConverter
    fun fromTimestamp( value: Long?) :
           Date {
        return Date(value ?: 0)
    }

    @TypeConverter
    fun dateToTimestamp(date :Date?)
            :Long {
        return date?.getTime() ?: 0
    }
}