package com.jguzmandev.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import com.jguzmandev.room.entity.Person

@Entity(tableName = "Cars",foreignKeys = [ForeignKey(
    entity = Person::class,
    parentColumns = ["PersonId"],
    childColumns = ["PersonCarFK"],onDelete = CASCADE
)])
data class Car(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "CarId")
    val carID: Int,
    @ColumnInfo(name = "CarBrand")
    val carBrand: String,
    @ColumnInfo(name = "PersonCarFK")
    val personId: Int = 0
)