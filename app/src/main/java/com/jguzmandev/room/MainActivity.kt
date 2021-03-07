package com.jguzmandev.room

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.jguzmandev.room.databinding.ActivityMainBinding
import com.jguzmandev.room.databinding.FindCarPersonIdBinding
import com.jguzmandev.room.entity.Car
import com.jguzmandev.room.entity.Contact
import com.jguzmandev.room.entity.Person
import com.jguzmandev.room.ui.CarActivity
import com.jguzmandev.room.ui.CarListByPersonActivity
import com.jguzmandev.room.ui.PersonActivity

class MainActivity : AppCompatActivity(), DialogInterface.OnClickListener {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val db by lazy { AppDatabase.getInstance(this) }

    private lateinit var dialogBinding: FindCarPersonIdBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_Demo2Room)

        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        binding.btnSavePerson.setOnClickListener {
            val personName = binding.etPersonName.text.toString()
            val person = Person(0, personName)
            db.personDao().add(person)
            Toast.makeText(this, "All Okis", Toast.LENGTH_SHORT).show()
        }

        binding.btnListPerson.setOnClickListener {
           Intent(this,PersonActivity::class.java).also {
               startActivity(it)
           }
        }

        binding.btnSaveCar.setOnClickListener {
            val brandCar = binding.etCarBrand.text.toString().trim()
            val personFK = binding.etPersonFk.text.toString().toInt()
            db.carDao().add(Car(0, brandCar, personFK))
        }

        binding.btnListCar.setOnClickListener {
            Intent(this,CarActivity::class.java).also {
                startActivity(it)
            }
        }

        binding.btnFindCarByPerson.setOnClickListener {
            dialogBinding = FindCarPersonIdBinding.inflate(layoutInflater)
            val alert = buildAlertDialog(dialogBinding.root)
                .setPositiveButton("Find", this)
                .setNegativeButton("Cancel", this)
                .setMessage("Find car by Person ID")
                .create()
            alert.show()
        }

        binding.btnSaveContact.setOnClickListener {
            val personID = binding.etPersonContactId.text.toString().toInt()
            val contactName = binding.etPersonContactName.text.toString()

            val contact = Contact(0, contactName, personID)
            db.contactDao().add(contact)
            Toast.makeText(this, "All Okis", Toast.LENGTH_LONG).show()
        }

        binding.btnListContact.setOnClickListener {
            val contactList = db.contactDao().getAllContacts()
            contactList.forEach {
                println(it)
            }
        }

        binding.btnListContactPersonJoin.setOnClickListener {
            val personID = binding.etPersonContactId.text.toString().toInt()
            val personContact = db.contactDao().getAllContactsByPersonID(personID)
            personContact?.let {  println(personContact) }?:run { println("Person's contact not found") }

        }
    }

    private fun buildAlertDialog(view: View) = AlertDialog.Builder(this)
        .setView(view)
        .setCancelable(true)

    override fun onClick(dialog: DialogInterface?, which: Int) {
        when (which) {
            DialogInterface.BUTTON_POSITIVE -> {
                val personID = dialogBinding.etPersonId.text.toString().toInt()
                Intent(this, CarListByPersonActivity::class.java).also {
                    it.putExtra("ID",personID)
                    startActivity(it)
                    this.overridePendingTransition(R.anim.enter,R.anim.enter)
                }
            }
            DialogInterface.BUTTON_NEGATIVE -> {
                dialog?.dismiss()
            }
        }
    }

}