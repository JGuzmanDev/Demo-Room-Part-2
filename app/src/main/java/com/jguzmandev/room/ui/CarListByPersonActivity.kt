package com.jguzmandev.room.ui

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.jguzmandev.room.AppDatabase
import com.jguzmandev.room.entity.Car
import com.jguzmandev.room.R
import com.jguzmandev.room.adapter.BaseAdapter
import com.jguzmandev.room.databinding.ActivityCarListByPersonBinding

class CarListByPersonActivity : AppCompatActivity() {

    private val binding by lazy { ActivityCarListByPersonBinding.inflate(layoutInflater) }
    private val carListPersonAdapter by lazy {
        BaseAdapter<Car>(R.layout.item_list) { holder, item ->
            with(holder.itemView) {
                findViewById<TextView>(R.id.tv_title).text = item.toString()
                findViewById<TextView>(R.id.tv_title).setOnClickListener {
                    Toast.makeText(this@CarListByPersonActivity, "$item", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
    private val db by lazy { AppDatabase.getInstance(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        this.intent?.extras?.let {
            val personID = it.getInt("ID", 0)
            db.carDao().findCarsByPersonId(personID).also {
                carListPersonAdapter.addItems(it)
                carListPersonAdapter.notifyDataSetChanged()
            }
        }
        setUpRecyclerView()


    }

    private fun setUpRecyclerView() {
        with(binding.rvCarPerson) {
            layoutManager = LinearLayoutManager(this@CarListByPersonActivity)
            adapter = carListPersonAdapter
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        this.overridePendingTransition(R.anim.enter,R.anim.exit)
    }
}