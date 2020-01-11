package com.example.teachinghelper

import android.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.lifecycle.ViewModelProviders
import com.example.teachinghelper.ViewModels.AreasViewModel
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.widget.Button


class AreaChoice : AppCompatActivity() {
    private lateinit var areasViewModel: AreasViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_area_choice)

        var buttonsLayout = findViewById(R.id.areaButtonsLayout) as LinearLayout;

        areasViewModel = ViewModelProviders.of(this).get(AreasViewModel::class.java)
        // TODO: actually get proper areas depending on chosen subject
        var areas = areasViewModel.allAreas

//        areas.forEach{
        for (area in areas) {
            val btnTag = Button(this).also {
                it.setLayoutParams(
                    ActionBar.LayoutParams(
                        ActionBar.LayoutParams.WRAP_CONTENT,
                        ActionBar.LayoutParams.WRAP_CONTENT
                    )
                )
                it.setText(area.name)
                it.setId(area.id)
            }
            buttonsLayout.addView(btnTag)
//            (findViewById(area.id) as Button).setOnClickListener(this)
        }
    }
}
