package com.armandoalvear.mathvein

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import java.util.*
import kotlin.concurrent.schedule

class MonitorActivity : AppCompatActivity() {

    //Initialize Button variable
    lateinit var toggleButton: Button
    //Initialize textView variable
    lateinit var bpmTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_monitor)

        //Get button from monitorToggleButton
        toggleButton = findViewById(R.id.monitorToggleBtn)
        //Get view from bpmView
        bpmTextView = findViewById(R.id.bpmView)

        //On click toggleButton
        toggleButton.setOnClickListener{
            val userID = 1;
            val func = 1;

            //Put elements inside JSON Object
            val jsonObject = JSONObject().apply{
                put("user_id", userID)
                put("func", func)
            }

            val url = "https://aa1191.000webhostapp.com/scripts/monitor_mode_app.php"
            //val timer = Timer("schedule", false)
            //Create a JSON Object Request
            val jsonObjectRequest = JsonObjectRequest(Request.Method.POST, url, jsonObject,
                    Response.Listener {

                        //val error = it.get("error")
                        //if(error!=0)

                        val monitorMode = it.get("monitor_mode")
                        println("MonitorMode = " + monitorMode)

                        if (monitorMode == 0) {
                            val func = 2;
                            val monitorMode = 1
                            toggleButton.text = "STOP"

                                val jsonObject = JSONObject().apply {
                                    put("user_id", userID)
                                    put("func", func)
                                    put("monitor_mode", monitorMode)
                                }
                                val jsonObjectRequest = JsonObjectRequest(Request.Method.POST, url, jsonObject,
                                        Response.Listener {
                                            val bpmAverage = it.get("bpm_average")
                                            val noInputFlag = it.get("no_input_flag")
                                            //while(true){
                                            //timer.schedule(5000) {

                                            val timer = Timer()
                                            val monitor = object : TimerTask(){
                                                override fun run(){

                                                }
                                            }
                                            timer.schedule(monitor, 0,5000)

                                                if (noInputFlag == 0) {
                                                    val func = 4
                                                    bpmTextView.text = bpmAverage.toString() + " BPM"
                                                    val jsonObject = JSONObject().apply {
                                                        put("user_id", userID)
                                                        put("func", func)
                                                    }
                                                    val jsonObjectRequest = JsonObjectRequest(Request.Method.POST, url, jsonObject,
                                                            Response.Listener {
                                                                val noInputFlag = it.get("no_input_flag")
                                                                val bpmAverage = it.get("bpm_average")
                                                                println("inside func 4")
                                                            },
                                                            Response.ErrorListener {
                                                                Toast.makeText(this, "Error3: Please try again",
                                                                        Toast.LENGTH_LONG).show()
                                                            })
                                                    Volley.newRequestQueue(this).add(jsonObjectRequest)
                                                }
                                                else if (noInputFlag == 1) {
                                                    val func = 4
                                                    bpmTextView.text = bpmAverage.toString() + " BPM"
                                                    //Set Notification "Pulse Sensor is not detecting a pulse."
                                                    //On OK
                                                }
                                        },
                                        Response.ErrorListener {
                                            Toast.makeText(this, "Error1: Please try again",
                                                    Toast.LENGTH_LONG).show()
                                        })

                                Volley.newRequestQueue(this).add(jsonObjectRequest)
                        }
                        else if (monitorMode == 1) {
                            val func = 3
                            val monitorMode = 0
                            val zero = 0
                            toggleButton.text = "START"
                            bpmTextView.text = zero.toString() + " BPM"

                            val jsonObject = JSONObject().apply {
                                put("user_id", userID)
                                put("func", func)
                                put("monitor_mode", monitorMode)
                            }
                            val jsonObjectRequest = JsonObjectRequest(Request.Method.POST, url, jsonObject,
                                    Response.Listener{

                                    },
                                    Response.ErrorListener {
                                        Toast.makeText(this, "Error2: Please try again ",
                                                Toast.LENGTH_LONG).show()
                                    })
                            Volley.newRequestQueue(this).add(jsonObjectRequest)
                        }
                    },
                    Response.ErrorListener {
                        Toast.makeText(this, "Failed!",
                                Toast.LENGTH_LONG).show()
                    })
            Volley.newRequestQueue(this).add(jsonObjectRequest)
        }
    }
}
