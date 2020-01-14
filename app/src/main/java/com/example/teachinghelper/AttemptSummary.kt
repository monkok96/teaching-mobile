package com.example.teachinghelper

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProviders
import com.example.teachinghelper.ViewModels.AnswerHistoryViewModel
import com.example.teachinghelper.ViewModels.AnswerViewModel
import com.example.teachinghelper.ViewModels.AreasViewModel
import com.example.teachinghelper.ViewModels.QuestionViewModel

class AttemptSummary : AppCompatActivity() {
    private val defaultValue = -1
    private val defaultValueLong = -1L
    private lateinit var areaModel: AreasViewModel
    private lateinit var answerHistoryModel: AnswerHistoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_attempt_summary)
        initializeModels()
        initializeData()


        val menuButton = findViewById<Button>(R.id.menuButton)
        menuButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initializeModels() {
        this.areaModel = ViewModelProviders.of(this).get(AreasViewModel::class.java)
        this.answerHistoryModel = ViewModelProviders.of(this).get(AnswerHistoryViewModel::class.java)
    }

    private fun initializeData() {
        val areaId = intent.getIntExtra("areaId", defaultValue)
        if (areaId == defaultValue) {
            throw Exception("areaId is not set")
        }

        initializeSubjectText(areaId)

        val attemptId = intent.getLongExtra("attemptId", defaultValueLong)
        if (attemptId == defaultValueLong) {
            throw Exception("attemptId is not set")
        }

        initializePointsSummary(attemptId)

        val data = ViewModelProviders.of(this).get(AnswerHistoryViewModel::class.java).getByAttemptId(attemptId)
    }

    private fun initializeSubjectText(areaId: Int) {
        val subjectTitleText = findViewById<TextView>(R.id.subjectTitle)
        val subjectWithArea = this.areaModel.getAreaWithSubjectById(areaId)
        subjectTitleText.text = subjectWithArea.getString()
    }

    private fun initializePointsSummary(attemptId: Long) {
        val correctAnswersText = findViewById<TextView>(R.id.correctAnswersText)
        val questionsInAttempt = this.answerHistoryModel.getQuestionsCountInAttempt(attemptId)
        val correctAnswersInAttempt = this.answerHistoryModel.getCorrectAnswersCountInAttempt(attemptId)

        correctAnswersText.text ="Poprawnych odpowiedzi: ${correctAnswersInAttempt.value} z ${questionsInAttempt.value}"
    }
}
