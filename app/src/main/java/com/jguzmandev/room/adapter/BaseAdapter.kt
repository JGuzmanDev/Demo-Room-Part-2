package com.jguzmandev.room.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class BaseAdapter<T>(
    private val layoutId:Int,
    private var listener:(BaseAdapter<T>.BaseViewHolder,itemSelected:T)->Unit
    ) : RecyclerView.Adapter<BaseAdapter<T>.BaseViewHolder>(){

    private var list = mutableListOf<T>()

    fun addItems(items:List<T>){
        list.addAll(items)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(layoutId,parent,false)
        return BaseViewHolder(view)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val itemSelected:T = list[position]
        listener.invoke(holder,itemSelected)
    }

    override fun getItemCount(): Int = list.size

    inner class BaseViewHolder(view:View) : RecyclerView.ViewHolder(view)
}

