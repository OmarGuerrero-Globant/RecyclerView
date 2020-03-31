package com.example.recyclerview

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private lateinit var mAdapter: RecyclerView.Adapter<*>
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var input : MutableList<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        myRecyclerView.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this)
        myRecyclerView.layoutManager = layoutManager
        input = ArrayList()
        addElements()
        mAdapter = MyAdapter(input)
        myRecyclerView.adapter = mAdapter


        val simpleItemTouchCallback: ItemTouchHelper.SimpleCallback = object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: ViewHolder,
                target: ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: ViewHolder, swipeDir: Int) {
                input.removeAt(viewHolder.adapterPosition)
                mAdapter.notifyItemRemoved(viewHolder.adapterPosition)
            }
        }
        val itemTouchHelper = ItemTouchHelper(simpleItemTouchCallback)
        itemTouchHelper.attachToRecyclerView(myRecyclerView)

        swipeRefresh.setOnRefreshListener {
            updateRecyclerView()
        }
    }

    private fun addElements(){
        (0..99).forEach { i ->
            input.add("Test$i")
        }
    }

    private fun updateRecyclerView() {
        input.clear()
        mAdapter.notifyDataSetChanged()
        addElements()
        mAdapter.notifyDataSetChanged()
        swipeRefresh.isRefreshing = false
        Toast.makeText(this, "Updated list", Toast.LENGTH_SHORT).show()
    }
}
