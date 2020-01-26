package com.example.teachinghelper.View

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.teachinghelper.R
import com.example.teachinghelper.View.ViewModels.AnswerViewModel


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ViewModelProviders.of(this).get(AnswerViewModel::class.java).getAnswersByQuestionId(1)

        val startGameButton = findViewById<Button>(R.id.StartGameButton)
        startGameButton.setOnClickListener {
            val intent = Intent(this, SubjectChoice::class.java)
            startActivity(intent)
        }

        val settings = findViewById<Button>(R.id.SettingsButton)
        settings.setOnClickListener {
            val intent = Intent(this, Settings::class.java)
            startActivity(intent)
        }

        var exitButton = findViewById<Button>(R.id.ExitButton)
        exitButton.setOnClickListener {
                finish()
                System.exit(0)
        }
    }
}
