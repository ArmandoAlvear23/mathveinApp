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
        val startAmPmFlag = work.startAmPm
        val endAmPmFlag = work.endAmPm
        var tempStart = TimeConvert().getTimeSplit(work.startTime)
        var tempEnd = TimeConvert().getTimeSplit(work.endTime)

        if (descValue.isNullOrBlank()){
            descValue = "(Not Specified)"
        }
        val intensityText = "Intensity: $intensityValue"
        val date = DateConvert().getDate(work.timestamp)
        val startTime = TimeConvert().getTime(tempStart, startAmPmFlag, 0)
        val endTime = TimeConvert().getTime(tempEnd, endAmPmFlag, 1)

        holder.workDescripton.setText(descValue)
        holder.workIntensity.setText(intensityText)
        holder.workStart.setText(startTime)
        holder.workEnd.setText(endTime)
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
