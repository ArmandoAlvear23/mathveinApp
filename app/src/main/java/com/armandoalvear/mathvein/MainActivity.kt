package com.armandoalvear.mathvein

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    lateinit var monitorModeButton: Button
    lateinit var vitalsButton: Button
    lateinit var eventsButton: Button
    lateinit var historyButton: Button
    lateinit var helpButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        monitorModeButton = findViewById(R.id.monitorBtn)
        vitalsButton = findViewById(R.id.vitalsBtn)
        eventsButton = findViewById(R.id.eventsBtn)
        historyButton = findViewById(R.id.historyBtn)
        helpButton = findViewById(R.id.helpBtn)

        monitorModeButton.setOnClickListener{
            val intent: Intent = Intent(applicationContext, MonitorActivity::class.java)
            startActivity(intent)
        }

        vitalsButton.setOnClickListener{
            val intent: Intent = Intent(applicationContext, VitalsActivity::class.java)
            startActivity(intent)
        }

        eventsButton.setOnClickListener{
            val intent: Intent = Intent(applicationContext, EventsActivity::class.java)
            startActivity(intent)
        }

        historyButton.setOnClickListener{
            val intent: Intent = Intent(applicationContext, HistoryActivity::class.java)
            startActivity(intent)
        }
    }
}




