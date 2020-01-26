package com.example.teachinghelper.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.lifecycle.ViewModelProviders
import com.example.teachinghelper.R
import com.example.teachinghelper.View.ViewModels.AnswerHistoryViewModel
import com.example.teachinghelper.View.ViewModels.AreasViewModel



class AttemptSummary : AppCompatActivity() {
    private val defaultValue = -1L
    private val defaultValueLong = -1L
    private lateinit var areaModel: AreasViewModel
    private lateinit var answerHistoryModel: AnswerHistoryViewModel
    private var attemptId = -1L
    private var areaId = -1L
    private var subjectId = -1L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_attempt_summary)
        initializeModels()
        initializeData()

        val detailsButton = findViewById<RelativeLayout>(R.id.AttemptDetailsButton)
        detailsButton.setOnClickListener{
            val intent = Intent(this, AttemptDetailsActivity::class.java)
            intent.putExtra("attemptId", attemptId)
            intent.putExtra("areaId", areaId)
            startActivity(intent)
        }

        val menuButton = findViewById<Button>(R.id.menuButton)
        menuButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java )
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent)
            finish()
        }

        val playAgainButton = findViewById<Button>(R.id.playAgainButton)
        playAgainButton.setOnClickListener{
            val intent = Intent(this, SubjectChoice::class.java )
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent)
            finish()
        }
    }

    private fun initializeModels() {
        this.areaModel = ViewModelProviders.of(this).get(AreasViewModel::class.java)
        this.answerHistoryModel = ViewModelProviders.of(this).get(AnswerHistoryViewModel::class.java)
    }

    private fun initializeData() {
        areaId = intent.getLongExtra("areaId", defaultValue)
        if (areaId == defaultValue) {
            throw Exception("areaId is not set")
        }

        initializeSubjectText(areaId)

        attemptId = intent.getLongExtra("attemptId", defaultValueLong)
        if (attemptId == defaultValueLong) {
            throw Exception("attemptId is not set")
        }

        initializePointsSummary(attemptId)

        subjectId = intent.getLongExtra("subjectId", defaultValue)
        if (subjectId == defaultValue) {
            throw Exception("subjectId is not set")
        }

    }

    private fun initializeSubjectText(areaId: Long) {
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

    override fun onBackPressed() {
//        super.onBackPressed()
        val intent = Intent(this, AreaChoice::class.java)
        intent.putExtra("subjectId", subjectId)
        startActivity(intent)
        finish()
    }
}
