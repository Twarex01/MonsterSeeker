package com.example.monsterseeker

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.monsterseeker.adapters.RecyclerAdapter
import com.example.monsterseeker.dtos.NewMonster
import com.example.monsterseeker.viewmodels.ListMonsterViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
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
            val adapter = RecyclerAdapter(value!!){ onItemClick() }

            recyclerview.adapter = adapter
            recyclerview.layoutManager = LinearLayoutManager(this)
        }

        val fab = findViewById<FloatingActionButton>(R.id.fab)

        //TODO: Rand
        fab.setOnClickListener(){
            Toast.makeText(this@MainActivity, "Monster added", Toast.LENGTH_SHORT).show()
            model.addListMonster(NewMonster("AddedMonster", "Description"))
        }
    }

    private fun onItemClick() {
        Toast.makeText(this@MainActivity, "Monster removed", Toast.LENGTH_SHORT).show()
    }
}