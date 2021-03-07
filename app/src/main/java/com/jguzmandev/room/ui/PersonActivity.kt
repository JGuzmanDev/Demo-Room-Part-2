package com.jguzmandev.room.ui

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.jguzmandev.room.AppDatabase
import com.jguzmandev.room.entity.Person
import com.jguzmandev.room.R
import com.jguzmandev.room.adapter.BaseAdapter
import com.jguzmandev.room.databinding.ActivityPersonBinding

class PersonActivity : AppCompatActivity() {

    private val binding by lazy { ActivityPersonBinding.inflate(layoutInflater) }
    private val db by lazy { AppDatabase.getInstance(this) }

    private val personAdapter by lazy {
        BaseAdapter<Person>(
            R.layout.item_list
        ) { holder, item ->
            with(holder.itemView) {
                findViewById<TextView>(R.id.tv_title).text = "${item.personID} ${item.personName}"
                findViewById<TextView>(R.id.tv_title).setOnClickListener {
                    Toast.makeText(this@PersonActivity, "$item", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setUpRecyclerView()

        db.personDao().getAllPeople().also { peopleList ->
            personAdapter.addItems(peopleList)
            personAdapter.notifyDataSetChanged()
            println(peopleList)
        }
    }

    private fun setUpRecyclerView() {
        with(binding.rv) {
            layoutManager = LinearLayoutManager(this@PersonActivity)
            adapter = personAdapter
            addItemDecoration(
                DividerItemDecoration(
                    this@PersonActivity,
                    DividerItemDecoration.HORIZONTAL
                )
            )
        }
    }
}