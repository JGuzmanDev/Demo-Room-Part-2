package com.jguzmandev.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.jguzmandev.room.entity.Contact
import com.jguzmandev.room.customentity.PersonContact

@Dao
interface ContactDAO {

    @Insert
    fun add(contact: Contact)

    @Query("SELECT * FROM CONTACTS")
    fun getAllContacts():List<Contact>

   @Query("SELECT * FROM People p INNER JOIN Contacts c ON p.PersonId = c.PersonContactFK WHERE p.PersonId = :personID")
   fun getAllContactsByPersonID(personID:Int):PersonContact?
}
