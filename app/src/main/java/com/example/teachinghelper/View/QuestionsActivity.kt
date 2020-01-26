package com.example.teachinghelper.View

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProviders
import com.example.teachinghelper.Database.Entities.Answer
import com.example.teachinghelper.Helpers.AnswersLogger
import com.example.teachinghelper.Helpers.PreditionModule.PredictionHandlersFactory
import com.example.teachinghelper.Helpers.PreditionModule.PredictionType
import com.example.teachinghelper.Helpers.PreditionModule.QuestionsSelector
import com.example.teachinghelper.View.ViewModels.AnswerViewModel
import com.example.teachinghelper.View.ViewModels.AreasViewModel
import com.example.teachinghelper.View.ViewModels.QuestionViewModel
import com.example.teachinghelper.Database.Models.QuestionAllInfo
import androidx.appcompat.app.AlertDialog
import com.example.teachinghelper.R


class QuestionsActivity : AppCompatActivity() {
    private lateinit var questionModel: QuestionViewModel
    private lateinit var answerModel: AnswerViewModel
    private lateinit var areaModel: AreasViewModel
    private lateinit var buttons: List<Button>
    private lateinit var selectedQuestions: List<QuestionAllInfo>
    private lateinit var answers: AnswersLogger
    private lateinit var defaultButtonBackground: Drawable
    private val answersCount = 3
    private var areaId: Long = -1
    private var subjectId: Long = -1

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
        val defaultValue = -1L
        areaId = intent.getLongExtra("areaId", defaultValue)

        if (areaId == defaultValue) {
            throw Exception("areaId is not set")
        }

        subjectId = intent.getLongExtra("subjectId", defaultValue)

        if (subjectId == defaultValue) {
            throw Exception("subjectId is not set")
        }

        initializeSubjectText()
        val QuestionSelector = QuestionsSelector(ViewModelProviders.of(this), PredictionHandlersFactory())
        this.selectedQuestions = QuestionSelector.get(Settings.selectedPrediction, this.areaId, this.answersCount)
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
                intent.putExtra("subjectId", subjectId)
                finish()
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
        return this.selectedQuestions.get(this.answers.answersCount())
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

    override fun onBackPressed() {
        val builder = AlertDialog.Builder(this)

        builder.setTitle("Opuszczanie podejścia")
        builder.setMessage("Czy na pewno chcesz opuścić to podejście? Nie zostanie ono zapisane.")
        builder.setPositiveButton("Tak") { _, _ ->
            val intent = Intent(this, AreaChoice::class.java)
            intent.putExtra("subjectId", subjectId)
            startActivity(intent)
            finish()
        }
        builder.setNegativeButton("Nie"
        ) { _, _ -> run {} }
        builder.show()
    }

}
