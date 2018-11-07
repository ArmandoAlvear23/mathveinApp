package com.armandoalvear.mathvein

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class EventsActivity : AppCompatActivity() {

    lateinit var sleepBtn: Button
    lateinit var workBtn: Button
    lateinit var exerciseBtn: Button
    lateinit var mealBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_events)

        sleepBtn = findViewById(R.id.sleepBtn)
        workBtn = findViewById(R.id.workBtn)
        exerciseBtn = findViewById(R.id.exerciseBtn)
        mealBtn = findViewById(R.id.mealBtn)

        sleepBtn.setOnClickListener{
            val intent: Intent = Intent(applicationContext, SleepActivity::class.java)
            startActivity(intent)
        }

        workBtn.setOnClickListener{
            val intent: Intent = Intent(applicationContext, WorkActivity::class.java)
            startActivity(intent)
        }

        exerciseBtn.setOnClickListener{
            val intent: Intent = Intent(applicationContext, ExerciseActivity::class.java)
            startActivity(intent)
        }

        mealBtn.setOnClickListener{
            val intent: Intent = Intent(applicationContext, MealActivity::class.java)
            startActivity(intent)
        }
    }
}
