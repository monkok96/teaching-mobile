package com.example.teachinghelper

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.teachinghelper.ViewModels.QuestionViewModel
import com.example.teachinghelper.ViewModels.SubjectViewModel


class MainActivity : AppCompatActivity() {
    private lateinit var questionsViewModel: QuestionViewModel
    private lateinit var subjectViewModel: SubjectViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val startGameButton = findViewById<Button>(R.id.StartGameButton)
        startGameButton.setOnClickListener {
            val intent = Intent(this, SubjectChoice::class.java)
            startActivity(intent)
        }

        var exitButton = findViewById<Button>(R.id.ExitButton)
        exitButton.setOnClickListener {
                finish()
                System.exit(0)
        }
    }
}
