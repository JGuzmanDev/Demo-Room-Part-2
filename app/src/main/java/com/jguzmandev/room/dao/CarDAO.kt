package com.jguzmandev.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.jguzmandev.room.entity.Car

@Dao
interface CarDAO {

    @Insert
    fun add(car: Car)

    @Query("SELECT * FROM cars")
    fun getAll(): List<Car>

    @Query("SELECT * FROM cars WHERE PersonCarFK = :personId")
    fun findCarsByPersonId(personId:Int):List<Car>
}