package com.example.monsterseeker

import android.content.Intent
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

    private lateinit var model : ListMonsterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)
        model = ViewModelProvider(this)[ListMonsterViewModel::class.java]

        model.loadListMonsters()

        model.getListMonsters().observe(this) {
            val value = model.getListMonsters().value
            val adapter = RecyclerAdapter(value!!,
                { name -> onButtonClick(name) },
                { name -> onCheckBoxClick(name) },
                { name -> onTextViewClick(name) })

            recyclerview.adapter = adapter
            recyclerview.layoutManager = LinearLayoutManager(this)
        }

        val fab = findViewById<FloatingActionButton>(R.id.fab)

        fab.setOnClickListener {
            onFabClick()
        }
    }

    //TODO: Rand
    private fun onFabClick() {
        Toast.makeText(this@MainActivity, "Monster added", Toast.LENGTH_SHORT).show()
        model.addListMonster(NewMonster("AddedMonster", "Description"))
    }

    private fun onButtonClick(name : String) {
        Toast.makeText(this@MainActivity, "Monster removed", Toast.LENGTH_SHORT).show()
        model.deleteListMonster(name)
    }

    private fun onCheckBoxClick(name : String) {
        Toast.makeText(this@MainActivity, "Check boxed", Toast.LENGTH_SHORT).show()
        model.favouriteListMonster(name)
    }

    private fun onTextViewClick(name : String) {
        Toast.makeText(this@MainActivity, "Monster opened", Toast.LENGTH_SHORT).show()

        val intent = Intent(this, DetailedActivity::class.java)
        intent.putExtra("Name", name)
        startActivity(intent)
    }
}