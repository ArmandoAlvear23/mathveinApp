package com.armandoalvear.mathvein

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class ExerciseAdapter(private val context: Context, private val list: List<Exercise>) : RecyclerView.Adapter<ExerciseAdapter.ViewHolder>() {

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.row_exercise, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val exercise = list[position]

        var descValue = exercise.desc
        val intensityValue = exercise.intense
        val startAmPmValue = exercise.startAmPm
        val endAmPmValue = exercise.endAmPm
        var tempStart = TimeConvert().getTimeSplit(exercise.startTime)
        var tempEnd = TimeConvert().getTimeSplit(exercise.endTime)

        if (descValue.isNullOrBlank()){
            descValue = "(None Specified)"
        }
        val startTime = TimeConvert().getTime(tempStart, startAmPmValue, 0)
        val endTime = TimeConvert().getTime(tempEnd, endAmPmValue, 1)
        val intensityFinal = "Intensity: $intensityValue"
        val date = DateConvert().getDate(exercise.timestamp)

        holder.exerciseDescripton.setText(descValue)
        holder.exerciseIntensity.setText(intensityFinal)
        holder.exerciseStart.setText(startTime)
        holder.exerciseEnd.setText(endTime)
        holder.exerciseDate.setText(date)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var exerciseDescripton: TextView
        var exerciseIntensity: TextView
        var exerciseStart: TextView
        var exerciseEnd: TextView
        var exerciseDate: TextView

        init {
            exerciseDescripton = itemView.findViewById(R.id.description_ex_history)
            exerciseIntensity = itemView.findViewById(R.id.intensity_ex_history)
            exerciseStart = itemView.findViewById(R.id.start_ex_history)
            exerciseEnd = itemView.findViewById(R.id.end_ex_history)
            exerciseDate = itemView.findViewById(R.id.date_ex_history)
        }
    }

}
