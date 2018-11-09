package com.armandoalvear.mathvein

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class HistoryActivity : AppCompatActivity() {

    private lateinit var vitalsButton: Button
    private lateinit var eventsButton: Button
    private lateinit var notificationsButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        vitalsButton = findViewById(R.id.vitalsBtn)
        eventsButton = findViewById(R.id.eventsBtn)
        notificationsButton = findViewById(R.id.notificationsBtn)

        vitalsButton.setOnClickListener{
            val intent: Intent = Intent(applicationContext, VitalsHistoryActivity::class.java)
            startActivity(intent)
        }

        eventsButton.setOnClickListener{
            val intent: Intent = Intent(applicationContext, EventsHistoryActivity::class.java)
            startActivity(intent)
        }

        notificationsButton.setOnClickListener{
            val intent: Intent = Intent(applicationContext, NotificationsHistoryActivity::class.java)
            startActivity(intent)
        }
    }
}
