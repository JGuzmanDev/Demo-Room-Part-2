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
import com.jguzmandev.room.databinding.ActivityCarBinding

class CarActivity : AppCompatActivity() {

    private val binding by lazy { ActivityCarBinding.inflate(layoutInflater) }

    private val carAdapter by lazy {
        BaseAdapter<Car>(R.layout.item_list) { holder, item ->
            with(holder.itemView) {
                findViewById<TextView>(R.id.tv_title).text = item.toString()
                findViewById<TextView>(R.id.tv_title).setOnClickListener {
                    Toast.makeText(this@CarActivity, "$item", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private val db by lazy { AppDatabase.getInstance(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        setUpRecyclerView()

        db.carDao().getAll().also {
            carAdapter.addItems(it)
            carAdapter.notifyDataSetChanged()
        }
    }

    private fun setUpRecyclerView() {
        with(binding.rvCar) {
            layoutManager = LinearLayoutManager(this@CarActivity)
            adapter = carAdapter
        }
    }
}