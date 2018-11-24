package com.armandoalvear.mathvein

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

@Suppress("DEPRECATION")
class SleepAdapter (private val context: Context, private val list: List<Sleep>) : RecyclerView.Adapter<com.armandoalvear.mathvein.SleepAdapter.ViewHolder>() {

        override fun getItemCount(): Int {
            return list.size
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val v = LayoutInflater.from(context).inflate(R.layout.row_sleep, parent, false)
            return ViewHolder(v)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val sleep = list[position]

            val startAmPmFlag = sleep.startAmPm
            val endAmPmFlag = sleep.endAmPm
            val tempStart = TimeConvert().getTimeSplit(sleep.startTime)
            val tempEnd = TimeConvert().getTimeSplit(sleep.endTime)
            val timestamp = sleep.timestamp

            val date = DateConvert().getDate(timestamp)
            val totalTime = TimeConvert().getTotalTime(tempStart, tempEnd, startAmPmFlag, endAmPmFlag)
            val startTime = TimeConvert().getTime(tempStart, startAmPmFlag, 0)
            val endTime = TimeConvert().getTime(tempEnd, endAmPmFlag, 1)

            holder.sleepTotal.setText(totalTime)
            holder.sleepStart.setText(startTime)
            holder.sleepEnd.setText(endTime)
            holder.sleepDate.setText(date)
        }

        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var sleepTotal: TextView
            var sleepStart: TextView
            var sleepEnd: TextView
            var sleepDate: TextView
            init {
                sleepTotal = itemView.findViewById(R.id.total_time_sleep_history)
                sleepStart = itemView.findViewById(R.id.start_sleep_history)
                sleepEnd = itemView.findViewById(R.id.end_sleep_history)
                sleepDate = itemView.findViewById(R.id.date_sleep_history)
            }
        }
}