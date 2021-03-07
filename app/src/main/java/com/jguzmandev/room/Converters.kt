package com.jguzmandev.room

import androidx.room.TypeConverter
import java.util.*

class Converters {

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) } ?: run { null }
    }

    @TypeConverter
    fun datToTimestamp(date:Date?):Long?{
        return date?.let { date.time?.toLong() }
    }
}