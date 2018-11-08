package com.armandoalvear.mathvein

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.SwitchCompat
import android.widget.Button
import kotlinx.android.synthetic.main.activity_work.*

class WorkActivity : AppCompatActivity() {

    lateinit var submitBtn: Button
    lateinit var switch_1: SwitchCompat
    lateinit var switch_2: SwitchCompat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work)

        submitBtn = findViewById(R.id.submitBtn)
        switch_1 = switch1 as SwitchCompat
        switch_2 = switch2 as SwitchCompat

        submitBtn.setOnClickListener{
            val userID = 1
            val startTime = startTimeField.text.toString()
            val endTime = endTimeField.text.toString()
            val sw1Status = if(switch_1.isChecked) 1 else 0
            val sw2Status = if(switch_2.isChecked) 1 else 0

        }
    }
}
