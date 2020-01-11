package com.example.teachinghelper

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.teachinghelper.Questions.QuestionListAdapter
import com.example.teachinghelper.ViewModels.QuestionViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private lateinit var questionsViewModel: QuestionViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )



        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = QuestionListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        questionsViewModel = ViewModelProviders.of(this).get(QuestionViewModel::class.java)

        try {
            adapter.setQuestions((questionsViewModel.allQuestions))
//            questionsViewModel.allQuestions.observe(this, Observer { questions ->
//                // Update the cached copy of the words in the adapter.
//                questions?.let { adapter.setQuestions(it) }
//            })
        } catch(ex: Exception) {}


    }
}
