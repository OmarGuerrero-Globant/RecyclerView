package com.example.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.row_layout.view.*


class MyAdapter
    (private var values: MutableList<String>) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    fun add(position: Int, item: String) {
        values.add(position, item)
        notifyItemInserted(position)
    }

    private fun remove(position: Int) {
        values.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_layout, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val name = values[position]
        holder.itemView.firstLine.text = name
        holder.itemView.firstLine.setOnClickListener { remove(position) }
        holder.itemView.secondLine.text = "Footer: $name"
    }

    override fun getItemCount(): Int {
        return values.size
    }

}