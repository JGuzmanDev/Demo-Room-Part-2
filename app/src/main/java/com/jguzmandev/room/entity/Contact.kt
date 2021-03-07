package com.jguzmandev.room.entity

import androidx.room.*
import androidx.room.ForeignKey.CASCADE
import com.jguzmandev.room.entity.Person

@Entity(tableName = "Contacts",foreignKeys =
    [ForeignKey(
        entity = Person::class,
        parentColumns = ["PersonId"],
        childColumns = ["PersonContactFK"],
        onDelete = CASCADE
    )],
    indices = [Index(value = ["PersonContactFK"] , unique = true)])
data class Contact(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ContactId")
    val Id:Int,
    @ColumnInfo(name="ContactName")
    val Name:String,
    @ColumnInfo(name="PersonContactFK")
    val PersonFKData:Int
)