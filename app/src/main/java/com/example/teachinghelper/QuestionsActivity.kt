package com.example.teachinghelper

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProviders
import com.example.teachinghelper.Entities.Answer
import com.example.teachinghelper.Helpers.AnswersLogger
import com.example.teachinghelper.ViewModels.AnswerViewModel
import com.example.teachinghelper.ViewModels.AreasViewModel
import com.example.teachinghelper.ViewModels.QuestionViewModel
import com.example.teachinghelper.readmodel.QuestionAllInfo

class QuestionsActivity : AppCompatActivity() {
    private lateinit var questionModel: QuestionViewModel
    private lateinit var answerModel: AnswerViewModel
    private lateinit var areaModel: AreasViewModel
    private lateinit var buttons: List<Button>
    private lateinit var allAreaQuestions: List<QuestionAllInfo>
    private lateinit var answers: AnswersLogger
    private lateinit var defaultButtonBackground: Drawable
    private val answersCount = 3
    private var areaId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questions)
        this.initialize()
        this.prepareViewForNewQuestion()
    }

    private fun initialize() {
        this.initializeAnswers()
        this.initializeAnswerButtons()
        this.initializeModels()
        this.initializeData()
        this.initializeCallbacks()
    }

    private fun initializeAnswers() {
        this.answers = AnswersLogger(ViewModelProviders.of(this))
    }

    private fun initializeAnswerButtons() {
        this.buttons = listOf(
            findViewById(R.id.answerA),
            findViewById(R.id.answerB),
            findViewById(R.id.answerC),
            findViewById(R.id.answerD)
        )

        this.defaultButtonBackground = findViewById<Button>(R.id.answerA).background
    }

    private fun initializeModels() {
        this.answerModel = ViewModelProviders.of(this).get(AnswerViewModel::class.java)
        this.questionModel = ViewModelProviders.of(this).get(QuestionViewModel::class.java)
        this.areaModel = ViewModelProviders.of(this).get(AreasViewModel::class.java)
    }

    private fun initializeData() {
        val defaultValue = -1
        areaId = intent.getIntExtra("areaId", defaultValue)

        if (areaId == defaultValue) {
            throw Exception("areaId is not set")
        }

        initializeSubjectText()

        this.allAreaQuestions = this.questionModel.byAreaId(areaId)
    }

    private fun initializeSubjectText() {
        val subjectTitleText = findViewById<TextView>(R.id.subjectTitle)
        val subjectWithArea = this.areaModel.getAreaWithSubjectById(areaId)
        subjectTitleText.text = subjectWithArea.getString()
    }

    private fun initializeCallbacks() {
        val nextButton = findViewById<Button>(R.id.nextButton)
        nextButton.setOnClickListener{
            if (this.answers.answersCount() < this.answersCount) {
                val answerCountText = "${this.answers.answersCount() + 1} z ${this.answersCount}"
                findViewById<TextView>(R.id.AnswerCount).text = answerCountText

                this.prepareViewForNewQuestion()
                this.buttons.forEach{
                    it.background = this.defaultButtonBackground
                }
            } else {
                val intent = Intent(this, AttemptSummary::class.java)
                intent.putExtra("attemptId", this.answers.save())
                intent.putExtra("areaId", areaId)
                startActivity(intent)
            }
        }
    }

    private fun prepareViewForNewQuestion() {
        findViewById<Button>(R.id.nextButton).visibility = View.INVISIBLE;
        val question = getRandomQuestion()

        findViewById<TextView>(R.id.questionContent).text = question?.content

        if (question === null) {
            throw Exception("Question is not defined")
        }

        val answers = this.getAnswersForQuestionWith(question.id).shuffled()

        if (this.buttons.size != answers.size) {
            throw Exception("Answers size is not equals to button array size")
        }

        this.buttons.forEachIndexed {index, element ->
            var currentButtonAnswer = answers[index]
            element.text = currentButtonAnswer.content

            element.setOnClickListener{
                this.clickAction(answers, currentButtonAnswer)
            }
        }
    }

    private fun getRandomQuestion(): QuestionAllInfo? {
        return this.allAreaQuestions.shuffled().firstOrNull()
    }

    private fun getAnswersForQuestionWith(id: Long): List<Answer> {
        return answerModel.getAnswersByQuestionId(id)
    }

    private fun clickAction(answers: List<Answer>, selected: Answer) {
        this.answers.log(selected)

        this.buttons.forEachIndexed{ index, currentButton ->
            if (answers[index] === selected || answers[index].isCorrect) {
                val answerColor = if (answers[index].isCorrect) Color.GREEN else Color.RED
                currentButton.setBackgroundColor(answerColor)
            }

            currentButton.setOnClickListener(null)
        }

        findViewById<Button>(R.id.nextButton).visibility = View.VISIBLE;
    }
}
