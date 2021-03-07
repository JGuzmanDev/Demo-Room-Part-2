package com.jguzmandev.room.customentity

import androidx.room.Embedded
import com.jguzmandev.room.entity.Contact
import com.jguzmandev.room.entity.Person

data class PersonContact(
    @Embedded val person: Person,
    @Embedded val contact: Contact
)