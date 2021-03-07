package com.jguzmandev.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "People")
data class Person(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="PersonId")
    val personID:Int,
    @ColumnInfo(name="PersonName")
    val personName:String,
    @ColumnInfo(name="personCreated")
    val created: Date? = Date()
)