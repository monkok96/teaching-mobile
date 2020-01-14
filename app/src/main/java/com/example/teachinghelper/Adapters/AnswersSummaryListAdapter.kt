package com.example.teachinghelper.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.teachinghelper.Entities.AnswersHistory
import com.example.teachinghelper.R

class AnswersSummaryListAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<AnswersSummaryListAdapter.QuestionViewHolder>() {


    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var questions = emptyList<AnswersHistory>()

    inner class QuestionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val questionItemView: TextView = itemView.findViewById(R.id.answerSummaryTest)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_answerssummary_item, parent, false)
        return QuestionViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        val current = questions[position]
//        holder.questionItemView.text = current.questionId.toString()
    }

    internal fun setAnswersSummary(questions: List<AnswersHistory>) {
        this.questions = questions
        notifyDataSetChanged()
    }

    override fun getItemCount() = questions.size
}