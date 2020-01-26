package com.example.teachinghelper.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import com.example.teachinghelper.R
import android.widget.ArrayAdapter
import com.example.teachinghelper.Helpers.PreditionModule.PredictionType


class Settings : AppCompatActivity() {

    companion object {
        var selectedPrediction = PredictionType.BASIC
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        val dropdown = findViewById<Spinner>(R.id.spinner)


        val map = mutableMapOf<String, Int>()
        PredictionType.values().iterator().forEach {
            map.put(it.name, it.ordinal)
        }

        val items = map.keys.toTypedArray()
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, items)
        dropdown.prompt = "Please select"
        dropdown.adapter = adapter
        dropdown.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedPrediction = PredictionType.valueOf(parent?.getItemAtPosition(position).toString())
            }
        }
    }
}
