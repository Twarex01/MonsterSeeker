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

        val title: TextView = findViewById(R.id.titleText)
        val description: TextView = findViewById(R.id.descriptionText)

        model.getDetailedMonster().observe(this) {
            val value = model.getDetailedMonster().value

            if(value != null)
            {
                title.text = value.name
                description.text = value.description
            }
        }
    }
}