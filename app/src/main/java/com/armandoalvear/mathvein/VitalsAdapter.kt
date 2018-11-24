package com.armandoalvear.mathvein

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class VitalsAdapter(private val context: Context, private val list: List<Vitals>) : RecyclerView.Adapter<VitalsAdapter.ViewHolder>() {

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.row_vitals, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val vitals = list[position]

        var systolic = "Systolic: " + vitals.systolic
        val diastolic = "Diastolic: " + vitals.diastolic
        val bloodSugar = "Blood Sugar: " + vitals.blood_sugar
        val date = DateConvert().getDate(vitals.timestamp)


        holder.vitalsSystolic.setText(systolic)
        holder.vitalsDiastolic.setText(diastolic)
        holder.vitalsBloodSugar.setText(bloodSugar)
        holder.vitalsDate.setText(date)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var vitalsSystolic: TextView
        var vitalsDiastolic: TextView
        var vitalsBloodSugar: TextView
        var vitalsDate: TextView

        init {
            vitalsSystolic = itemView.findViewById(R.id.systolic_vitals_history)
            vitalsDiastolic = itemView.findViewById(R.id.diastolic_vitals_history)
            vitalsBloodSugar = itemView.findViewById(R.id.blood_sugar_vitals_history)
            vitalsDate = itemView.findViewById(R.id.date_vitals_history)
        }
    }

}
