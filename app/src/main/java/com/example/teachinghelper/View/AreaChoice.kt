package com.example.teachinghelper.View

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.lifecycle.ViewModelProviders
import com.example.teachinghelper.View.ViewModels.AreasViewModel
import android.widget.Button
import android.widget.TextView
import com.example.teachinghelper.Database.Entities.Area
import com.example.teachinghelper.R
import com.example.teachinghelper.View.ViewModels.SubjectViewModel


class AreaChoice : AppCompatActivity() {
    private lateinit var areasViewModel: AreasViewModel
    private lateinit var subjectViewModel: SubjectViewModel
    private var subjectId: Long = -1L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_area_choice)

        val defaultValue = -1L
        subjectId = intent.getLongExtra("subjectId", defaultValue)
        if (subjectId == defaultValue) {
            throw Exception("subjectId is not set")
        }

        setSubjectName()
        setButtons()
    }

    private fun setSubjectName() {
        subjectViewModel = ViewModelProviders.of(this).get(SubjectViewModel::class.java)
        val subjectName =  subjectViewModel.getSubjectById(subjectId)
        val tmp = subjectName.name
        var subjectNameText = findViewById<TextView>(R.id.subjectName)
        subjectNameText.text = tmp
    }


    private fun setButtons() {
        var buttonsLayout = findViewById<LinearLayout>(R.id.areaButtonsLayout)

        var layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        layoutParams.setMargins(0, 50, 0, 0)

        var areas = getAreas()
        for (area in areas) {
            val btnTag = Button(this).also {
                it.layoutParams = layoutParams
                it.text = area.name
                it.setBackgroundResource(R.drawable.round_button)
                it.setTextColor(Color.parseColor("#ffffff"))
            }
            buttonsLayout.addView(btnTag)

            btnTag.setOnClickListener {
                val intent = Intent(this, QuestionsActivity::class.java)
                intent.putExtra("areaId", area.id)
                intent.putExtra("subjectId", subjectId)
                //finish()
                startActivity(intent)
                finish()
            }
        }
    }

    private fun getAreas(): List<Area> {
        areasViewModel = ViewModelProviders.of(this).get(AreasViewModel::class.java)
        return areasViewModel.areasBySubject(subjectId)
    }
}
