package com.example.teachinghelper.Adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.teachinghelper.AttemptDetailsActivity
import com.example.teachinghelper.Entities.AnswersHistory
import com.example.teachinghelper.R
import com.example.teachinghelper.readmodel.AttemptDetails
import kotlinx.android.synthetic.main.activity_questions.view.*

class AnswersSummaryListAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<AnswersSummaryListAdapter.QuestionViewHolder>() {


    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var attemptDetails = emptyList<AttemptDetails>()

    inner class QuestionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val questionItemView: TextView = itemView.findViewById(R.id.questionContent)
        val properAnswerView: TextView = itemView.findViewById(R.id.properAnswer)
        val chosenAnswerView: TextView = itemView.findViewById(R.id.chosenAnswer)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_answerssummary_item, parent, false)
        return QuestionViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        val current = attemptDetails[position]
        holder.questionItemView.text = "${position + 1}. ${current.questionContent}"
        holder.properAnswerView.text = "Poprawna odpowiedź: ${current.correctAnswerContent}"
        holder.chosenAnswerView.text = "Udzielona odpowiedź: ${current.chosenAnswerContent}"
        val answerColor = if (current.isCorrect()) Color.parseColor("#41C200") else Color.RED
        holder.chosenAnswerView.setTextColor(answerColor)
    }

    internal fun setAnswersSummary(details: List<AttemptDetails>) {
        this.attemptDetails = details
        notifyDataSetChanged()
    }

    override fun getItemCount() = attemptDetails.size
}