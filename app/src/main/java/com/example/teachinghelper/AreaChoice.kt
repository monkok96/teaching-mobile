package com.example.teachinghelper

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.lifecycle.ViewModelProviders
import com.example.teachinghelper.ViewModels.AreasViewModel
import android.widget.Button
import android.widget.TextView
import com.example.teachinghelper.ViewModels.SubjectViewModel


class AreaChoice : AppCompatActivity() {
    private lateinit var areasViewModel: AreasViewModel
    private lateinit var subjectViewModel: SubjectViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_area_choice)

        val subjectId = intent.getIntExtra("subjectId", 1)
        subjectViewModel = ViewModelProviders.of(this).get(SubjectViewModel::class.java)
        val subjectName =  subjectViewModel.getSubjectById(subjectId).name



        var subjectNameText = findViewById<TextView>(R.id.subjectName)
        subjectNameText.text = subjectName

        areasViewModel = ViewModelProviders.of(this).get(AreasViewModel::class.java)

        var areas = areasViewModel.areasBySubject(subjectId)

        var buttonsLayout = findViewById(R.id.areaButtonsLayout) as LinearLayout;

        var layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        layoutParams.setMargins(0, 50, 0, 0)

        for (area in areas) {
            val btnTag = Button(this).also {
                it.layoutParams = layoutParams
                it.text = area.name
                it.id = area.id
                it.setBackgroundResource(R.drawable.round_button)
                it.setTextColor(Color.parseColor("#ffffff"))
            }
            buttonsLayout.addView(btnTag)
//            (findViewById(area.id) as Button).setOnClickListener(this)
        }
    }
}
