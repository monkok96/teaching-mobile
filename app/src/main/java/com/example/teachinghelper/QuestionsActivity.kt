package com.example.teachinghelper

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.ViewModelProviders
import com.example.teachinghelper.Entities.Answer
import com.example.teachinghelper.ViewModels.AnswerViewModel
import com.example.teachinghelper.ViewModels.QuestionViewModel
import java.util.*

class QuestionsActivity : AppCompatActivity() {

    private lateinit var questionModel: QuestionViewModel
    private lateinit var answerModel: AnswerViewModel
    private lateinit var buttons: List<Button>
    private var areaId = 1;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questions)

        questionModel = ViewModelProviders.of(this).get(QuestionViewModel::class.java)
        answerModel = ViewModelProviders.of(this).get(AnswerViewModel::class.java)

        var data = questionModel.byAreaId(this.areaId);
        var data2 = answerModel.getAnswersByQuestionId(data[0].id).shuffled();

        this.initAnswerButtons()

        this.buttons.forEachIndexed {index, element ->
            var currentData = data2[index]
            element.text = currentData.content
            element.setOnClickListener{
                this.clickAction(element, currentData)
            }
        }
    }


    private fun clickAction(element: Button, data: Answer) {
        var color: Int
        if(data.isCorrect) {
            color = Color.GREEN
        } else {
            color = Color.RED
        }

        element.setBackgroundColor(color)
    }

    private fun initAnswerButtons() {
        this.buttons = listOf(
            findViewById(R.id.answerA),
            findViewById(R.id.answerB),
            findViewById(R.id.answerC),
            findViewById(R.id.answerD)
        )
    }
}
