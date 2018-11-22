package com.armandoalvear.mathvein

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


class WorkAdapter(private val context: Context, private val list: List<Work>) : RecyclerView.Adapter<WorkAdapter.ViewHolder>() {

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.row_exercise, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val work = list[position]
        var descValue = work.desc
        val intensityValue = work.intense
        val startAmPmValue = work.startAmPm
        val endAmPmValue = work.endAmPm
        var startTimeFinal = work.startTime.toString().substring(0,5)
        var endTimeFinal = work.endTime.toString().substring(0,5)

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
        val date = "Date: " + work.timestamp.toString().substringBefore(' ')

        holder.workDescripton.setText(descValue)
        holder.workIntensity.setText(intensityText)
        holder.workStart.setText(startTimeFinal)
        holder.workEnd.setText(endTimeFinal)
        holder.workDate.setText(date)

    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var workDescripton: TextView
        var workIntensity: TextView
        var workStart: TextView
        var workEnd: TextView
        var workDate: TextView

        init {

            workDescripton = itemView.findViewById(R.id.description_ex_history)
            workIntensity = itemView.findViewById(R.id.intensity_ex_history)
            workStart = itemView.findViewById(R.id.start_ex_history)
            workEnd = itemView.findViewById(R.id.end_ex_history)
            workDate = itemView.findViewById(R.id.date_ex_history)
        }
    }

}
