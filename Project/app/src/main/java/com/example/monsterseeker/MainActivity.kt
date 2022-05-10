package com.example.monsterseeker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.monsterseeker.adapters.RecyclerAdapter
import com.example.monsterseeker.models.ListMonster

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)

        recyclerview.layoutManager = LinearLayoutManager(this)

        //Temporary
        val data = ArrayList<ListMonster>()

        for (i in 1..10) {
            data.add(ListMonster("Monster $i", false))
        }

        val adapter = RecyclerAdapter(data)

        recyclerview.adapter = adapter
    }
}