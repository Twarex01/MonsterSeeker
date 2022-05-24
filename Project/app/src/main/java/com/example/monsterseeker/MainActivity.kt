package com.example.monsterseeker

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.monsterseeker.adapters.RecyclerAdapter
import com.example.monsterseeker.dtos.NewMonster
import com.example.monsterseeker.viewmodels.ListMonsterViewModel
import com.example.monsterseeker.viewmodels.MainViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.FirebaseApp
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.FirebaseAnalytics.Event.SELECT_ITEM
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import timber.log.Timber.Forest.plant
import kotlin.random.Random


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var model : ListMonsterViewModel

    private lateinit var firebaseAnalytics: FirebaseAnalytics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (BuildConfig.DEBUG) {
            plant(Timber.DebugTree())
        }

        Timber.log(1, "Hello Timber!")

        Log.d("DebugLog", "Hello")

        FirebaseApp.initializeApp(this);

        // Obtain the FirebaseAnalytics instance.
        firebaseAnalytics = Firebase.analytics

        val mainModel = ViewModelProvider(this)[MainViewModel::class.java]
        mainModel.fetchMonsters()

        val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)
        model = ViewModelProvider(this)[ListMonsterViewModel::class.java]

        model.loadListMonsters()

        model.getListMonsters().observe(this) {
            val value = model.getListMonsters().value

            if(value != null)
            {
                val adapter = RecyclerAdapter(value,
                    { name -> onButtonClick(name) },
                    { name -> onCheckBoxClick(name) },
                    { name -> onTextViewClick(name) })

                recyclerview.adapter = adapter
                recyclerview.layoutManager = LinearLayoutManager(this)
            }
        }

        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.APP_OPEN){ this }

        val fab = findViewById<FloatingActionButton>(R.id.fab)

        fab.setOnClickListener {
            onFabClick()
        }

        val crashButton = Button(this)
        crashButton.text = "Test Crash"
        crashButton.setOnClickListener {
            throw RuntimeException("Test Crash") // Force a crash
        }

        addContentView(crashButton, ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT))
    }

    private fun onFabClick() {
        Toast.makeText(this@MainActivity, "Monster added", Toast.LENGTH_SHORT).show()
        model.addListMonster(NewMonster("AddedMonster " + Random.nextInt(), "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas rutrum euismod ultrices. Vestibulum faucibus erat placerat neque commodo laoreet. Nulla facilisi. Vivamus sed quam urna. Sed interdum magna ac metus eleifend pretium. Mauris et sapien eget velit aliquet convallis id nec odio. Aliquam fringilla est non mi interdum, eu semper velit volutpat. Praesent a felis faucibus, tristique urna eget, tempus urna. Pellentesque in ex orci. Integer eget nulla pellentesque, pellentesque ex nec, tempor ligula. Morbi a turpis mollis, pellentesque tortor non, pharetra nunc. Nullam dui nibh, auctor dictum nisi vel, scelerisque sagittis nisl.\n" +
                "\n" +
                "Nullam mattis convallis erat, nec auctor est viverra sed. Curabitur libero nibh, fermentum id ligula eget, semper tempus mi. Vivamus imperdiet est at nunc varius accumsan. Vestibulum vel massa ut tellus elementum eleifend. Etiam at molestie est, eu condimentum nulla. Interdum et malesuada fames ac ante ipsum primis in faucibus. Maecenas sit amet ligula vel tellus faucibus cursus vitae eu turpis.\n" +
                "\n" +
                "Nam eu nibh metus. Sed fermentum mattis nisl, non pretium sapien pretium a. Suspendisse id sem nec diam convallis viverra quis a eros. Donec pharetra a magna vel vehicula. Donec blandit lorem posuere lectus rhoncus, sed cursus lectus dignissim. Ut orci massa, feugiat at enim semper, lobortis malesuada velit. Donec quis vestibulum lectus, nec placerat felis. Donec non interdum ex, at vestibulum mi. Mauris ultrices, justo eget tempor tempus, dui lacus lobortis nisl, in tristique leo tortor blandit ex. Suspendisse sed lacus in ipsum luctus tincidunt non non ex.\n" +
                "\n" +
                "In hendrerit auctor massa, eleifend sollicitudin tortor elementum quis. Interdum et malesuada fames ac ante ipsum primis in faucibus. Aliquam at lectus sit amet dui efficitur efficitur. Proin felis felis, congue nec porta at, sollicitudin eu odio. In commodo, dui at malesuada lacinia, dui ligula facilisis felis, tristique pretium orci nibh sed nisi. Donec sem metus, dapibus sed est non, tincidunt tristique erat. Suspendisse vestibulum metus ut venenatis ornare. Nullam condimentum lacus ut felis ornare semper.\n" +
                "\n" +
                "Etiam lobortis et sem id finibus. Sed sed libero fermentum, fermentum elit in, bibendum nisi. Nullam rutrum interdum orci ut mollis. Nullam feugiat lectus eget enim ultricies porta. Vestibulum nulla risus, sodales sed rhoncus sit amet, congue in erat. Morbi tempus pharetra fringilla. Vestibulum vestibulum risus sed tempor vulputate. Suspendisse interdum diam in dictum varius."))
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

        firebaseAnalytics.logEvent(SELECT_ITEM) {
            param(FirebaseAnalytics.Param.ITEM_NAME, name)
        }

        val intent = Intent(this, DetailedActivity::class.java)
        intent.putExtra("Name", name)
        startActivity(intent)
    }
}