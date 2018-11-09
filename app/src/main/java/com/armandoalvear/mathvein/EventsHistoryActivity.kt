package com.armandoalvear.mathvein

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class EventsHistoryActivity : AppCompatActivity() {

    private lateinit var mealButton: Button
    private lateinit var workButton: Button
    private lateinit var exerciseButton: Button
    private lateinit var sleepButton: Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_events_history)

        mealButton = findViewById(R.id.mealBtn)
        workButton = findViewById(R.id.workBtn)
        exerciseButton = findViewById(R.id.exerciseBtn)
        sleepButton = findViewById(R.id.sleepBtn)

        mealButton.setOnClickListener{
            val intent: Intent = Intent(applicationContext, MealHistoryActivity::class.java)
            startActivity(intent)
        }

        workButton.setOnClickListener{
            val intent: Intent = Intent(applicationContext, WorkHistoryActivity::class.java)
            startActivity(intent)
        }

        exerciseButton.setOnClickListener{
            val intent: Intent = Intent(applicationContext, ExerciseHistoryActivity::class.java)
            startActivity(intent)
        }

        sleepButton.setOnClickListener{
            val intent: Intent = Intent(applicationContext, SleepHistoryActivity::class.java)
            startActivity(intent)
        }

    }
}
