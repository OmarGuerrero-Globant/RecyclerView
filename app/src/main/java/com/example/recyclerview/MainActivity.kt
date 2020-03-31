package com.example.recyclerview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private lateinit var mAdapter: RecyclerView.Adapter<*>
    private lateinit var layoutManager: RecyclerView.LayoutManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        myRecyclerView.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this)
        myRecyclerView.layoutManager = layoutManager
        val input: MutableList<String> = ArrayList()
        (0..99).forEach { i ->
            input.add("Test$i")
        }
        mAdapter = MyAdapter(input)
        myRecyclerView.adapter = mAdapter
    }
}
