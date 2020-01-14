package com.example.teachinghelper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.teachinghelper.Adapters.AnswersSummaryListAdapter
import com.example.teachinghelper.Entities.Attempt
import com.example.teachinghelper.ViewModels.AnswerHistoryViewModel
import com.example.teachinghelper.ViewModels.AreasViewModel

class AttemptDetailsActivity : AppCompatActivity() {
    private val defaultValue = -1
    private val defaultValueLong = -1L
    private lateinit var areaModel: AreasViewModel
    private lateinit var answerHistoryModel: AnswerHistoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_attempt_details)

        initializeModels()
        initializeData()

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

        initializeList(attemptId)

    }

    private fun initializeSubjectText(areaId: Int) {
        val subjectTitleText = findViewById<TextView>(R.id.subjectTitle)
        val subjectWithArea = this.areaModel.getAreaWithSubjectById(areaId)
        subjectTitleText.text = subjectWithArea.getString()
    }

    private fun initializeList(attemptId: Long) {

        val recyclerView = findViewById<RecyclerView>(R.id.answersSummaryRecyclerView)
        val adapter = AnswersSummaryListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val attemptInfo = answerHistoryModel.getByAttemptId(attemptId)
        adapter.setAnswersSummary(attemptInfo)
    }
}
