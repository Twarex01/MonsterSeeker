package com.example.monsterseeker

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.monsterseeker.adapters.RecyclerAdapter
import com.example.monsterseeker.viewmodels.ListMonsterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)
        val model = ViewModelProvider(this)[ListMonsterViewModel::class.java]

        model.loadListMonsters()

        model.getListMonsters().observe(this) {
            val value = model.getListMonsters().value
            val adapter = RecyclerAdapter(value!!)

            recyclerview.adapter = adapter
            recyclerview.layoutManager = LinearLayoutManager(this)
        }
    }
}