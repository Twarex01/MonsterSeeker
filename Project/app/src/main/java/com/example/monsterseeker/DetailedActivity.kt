package com.example.monsterseeker

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.monsterseeker.viewmodels.DetailedMonsterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailedActivity : AppCompatActivity() {

    lateinit var model : DetailedMonsterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed)

        model = ViewModelProvider(this)[DetailedMonsterViewModel::class.java]

        val name = intent.getStringExtra("Name") ?: return

        model.loadDetailedMonster(name)

        val monster = model.getDetailedMonster()

        val title: TextView = findViewById(R.id.titleText)
        val description: TextView = findViewById(R.id.descriptionText)

        //TODO: Dao
        title.text = monster.value!!.name
        description.text = monster.value!!.description
    }
}