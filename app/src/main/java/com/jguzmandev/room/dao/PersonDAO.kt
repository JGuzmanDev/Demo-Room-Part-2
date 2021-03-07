package com.jguzmandev.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jguzmandev.room.entity.Person

@Dao
interface PersonDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(person: Person)

    @Query("SELECT * FROM people")
    fun getAllPeople():List<Person>
}