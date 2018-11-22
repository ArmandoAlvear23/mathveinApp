package com.armandoalvear.mathvein

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewDebug
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
        var startTimeFinal = exercise.startTime.toString().substring(0,5)
        var endTimeFinal = exercise.endTime.toString().substring(0,5)

        if (descValue.isNullOrBlank()){
            descValue = "(Not Specified)"
        }
        if(startTimeFinal.substring(0,1)=="0"){
            startTimeFinal = startTimeFinal.substring(1,5)
        }
        if(endTimeFinal.substring(0,1)=="0"){
            endTimeFinal = endTimeFinal.substring(1,5)
        }

        val intensityText = "Intensity: $intensityValue"
        if (startAmPmValue == 0){
            startTimeFinal= "Start: $startTimeFinal a.m."
        }else{
            startTimeFinal = "Start: $startTimeFinal p.m."
        }

        if (endAmPmValue == 0){
            endTimeFinal = "End: $endTimeFinal a.m."
        }else{
            endTimeFinal = "End: $endTimeFinal p.m."
        }
        val date = "Date: " + exercise.timestamp.toString().substringBefore(' ')

        holder.exerciseDescripton.setText(descValue)
        holder.exerciseIntensity.setText(intensityText)
        holder.exerciseStart.setText(startTimeFinal)
        holder.exerciseEnd.setText(endTimeFinal)
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
