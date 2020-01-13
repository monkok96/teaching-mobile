package com.example.teachinghelper

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.lifecycle.ViewModelProviders
import com.example.teachinghelper.ViewModels.SubjectViewModel

class SubjectChoice : AppCompatActivity() {
    private lateinit var subjectViewModel: SubjectViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subject_choice)


        val mathsButton = findViewById<RelativeLayout>(R.id.MathsButtonLayout)
        val englishButton = findViewById<RelativeLayout>(R.id.EnglishButtonLayout)
        val mathsName = findViewById<TextView>(R.id.mathsText)
        val englishName = findViewById<TextView>(R.id.englishText)

        subjectViewModel = ViewModelProviders.of(this).get(SubjectViewModel::class.java)
        var maths = subjectViewModel.subjectByName(mathsName.text.toString())
        var english = subjectViewModel.subjectByName(englishName.text.toString())

        mathsButton.setOnClickListener {
            val intent = Intent(this, AreaChoice::class.java)
            intent.putExtra("subjectId", maths.id)
            startActivity(intent)
        }

        englishButton.setOnClickListener {
            val intent = Intent(this, AreaChoice::class.java)
            intent.putExtra("subjectId", english.id)
            startActivity(intent)
        }
    }
}
