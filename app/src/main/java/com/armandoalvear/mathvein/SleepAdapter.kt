package com.armandoalvear.mathvein

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.meals_list.view.*
import java.sql.Time

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
            var startTimeFinal = sleep.startTime.toString().substring(0,5)
            var endTimeFinal = sleep.endTime.toString().substring(0,5)
            var timestamp = sleep.timestamp

            val date = DateConvert(timestamp).dateCon(timestamp)


            //Total Time Function: Getters
            val startHr = startTimeFinal.substringBefore(':').toInt()
            val startMin = startTimeFinal.substring(3,5).toInt()
            val endHr = endTimeFinal.substringBefore(':').toInt()
            val endMin = endTimeFinal.substring(3,5).toInt()

            //Total Time Function: Flags and values
            var finalHr = 0
            var finalMin = 0
            var minVal = 0
            var minusHr = 0

            //Total Time Function: Function Logic
            if(startAmPmFlag == 0 && endAmPmFlag == 1 || startAmPmFlag == 1 && endAmPmFlag == 0){
                if (startMin <= endMin){
                    finalMin = endMin - startMin
                }
                else if (startMin > endMin){
                    minVal = (60 - startMin) + endMin
                    finalMin = (minVal).mod(60)
                    minusHr = 1
                }
                if (startHr > endHr){
                    if (startHr == 12){
                        finalHr = startHr + endHr
                    }
                    else {
                        finalHr = (12 - startHr) + endHr
                    }
                }
                else if (startHr < endHr){
                    if (endHr == 12){
                        finalHr = endHr - startHr
                    }
                    else{
                        finalHr = (12 - startHr) + endHr
                    }
                }
                else{
                    finalHr = 12
                }
            }
            else{
                if (startMin <= endMin){
                    finalMin = endMin - startMin
                }
                else{
                    minVal = (60 - startMin) + endMin
                    finalMin = (minVal).mod(60)
                    minusHr = 1
                }
                if (startHr > endHr){
                    if (startHr == 12){
                        finalHr = endHr
                    }
                    else{
                        finalHr = 24 - (startHr - endHr)
                    }
                }
                else if (startHr > endHr){
                    if (endHr == 12){
                        finalHr = 12 + (endHr - startHr)
                    }
                    else{
                        finalHr = endHr - startHr
                    }
                }
                else{
                    finalHr = 0
                }
            }
            if (minVal >= 60){
                finalHr = finalHr + 1
            }
            if (minusHr == 1){
                finalHr = finalHr - 1
            }

            var totalTimeText = ""

            if (finalMin == 0){
                if(finalHr == 0){
                    totalTimeText = "No Sleep"
                }
                else if (finalHr == 1){
                    totalTimeText = "$finalHr hr"
                }
                else{
                    totalTimeText = "$finalHr hrs"
                }
            }
            else{
                if (finalHr == 0){
                    totalTimeText = "$finalMin min"
                }
                else if (finalHr == 1){
                    totalTimeText = "$finalHr hr  $finalMin min"
                }
                else{
                    totalTimeText = "$finalHr hrs  $finalMin min"
                }
            }
            //End Total Time Function

            //Time Function: check for zero in hr
            if(startTimeFinal.substring(0,1)=="0"){
                startTimeFinal = startTimeFinal.substring(1,5)
            }
            if(endTimeFinal.substring(0,1)=="0"){
                endTimeFinal = endTimeFinal.substring(1,5)
            }

            //Time Function: am/pm setter
            if (startAmPmFlag == 0){
                startTimeFinal= "Start: $startTimeFinal a.m."
            }else{
                startTimeFinal = "Start: $startTimeFinal p.m."
            }

            if (endAmPmFlag == 0){
                endTimeFinal = "End: $endTimeFinal a.m."
            }else{
                endTimeFinal = "End: $endTimeFinal p.m."
            }


            holder.sleepTotal.setText(totalTimeText)
            holder.sleepStart.setText(startTimeFinal)
            holder.sleepEnd.setText(endTimeFinal)
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