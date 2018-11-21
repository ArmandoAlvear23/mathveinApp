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
    private lateinit var toggleButton: Button
    //Initialize textView variable
    private lateinit var bpmTextView: TextView
    var stop = 0
    private val mHandler = Handler()
    val userID = 1
    val url = "https://aa1191.000webhostapp.com/scripts/monitor_mode_app.php"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_monitor)

        //Get button from monitorToggleButton
        toggleButton = findViewById(R.id.monitorToggleBtn)
        //Get view from bpmView
        bpmTextView = findViewById(R.id.bpmView)

        //On click toggleButton
        toggleButton.setOnClickListener{
            val func = 1;
            //Put elements inside JSON Object
            val jsonObject = JSONObject().apply{
                put("user_id", userID)
                put("func", func)
            }
            //Create a JSON Object Request
            val jsonObjectRequest = JsonObjectRequest(Request.Method.POST, url, jsonObject,
                    Response.Listener {
                        val monitorMode = it.get("monitor_mode")
                        println("MonitorMode:  $monitorMode")

                        //Turn on Monitor mode if OFF
                        if (monitorMode == 0) {
                            val func = 2;
                            val monitorMode = 1
                            stop = 0
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

                                                    if (noInputFlag == 0) {
                                                        val handler = Handler()
                                                        Thread(Runnable {
                                                            try {
                                                                while (stop==0) {
                                                                    println("Interrupt: " + Thread.currentThread().isInterrupted)
                                                                    println("inside while loop")
                                                                    println(bpmAverage)
                                                                    val func = 4
                                                                    //bpmTextView.text = bpmAverage.toString() + " BPM"
                                                                    println("after setting bpmtextview")
                                                                    val jsonObject = JSONObject().apply {
                                                                        put("user_id", userID)
                                                                        put("func", func)
                                                                    }
                                                                    val jsonObjectRequest = JsonObjectRequest(Request.Method.POST, url, jsonObject,
                                                                            Response.Listener {
                                                                                println("inside textView")
                                                                                val bpmAverage = it.get("bpm_average")
                                                                                val monitorMode = it.get("monitor_mode")
                                                                                println(bpmAverage)
                                                                                if (monitorMode == 1) {
                                                                                    handler.post {
                                                                                        bpmTextView.text = bpmAverage.toString() + " BPM"
                                                                                    }
                                                                                } else if (monitorMode == 0) {
                                                                                    println("inside interrupt")
                                                                                    println("monitorMode: $monitorMode")
                                                                                    Thread.currentThread().interrupt()
                                                                                    handler.post{
                                                                                        bpmTextView.text = "0 BPM"
                                                                                        stop = 1
                                                                                    }
                                                                                    println("Interrupt: " + Thread.currentThread().isInterrupted)

                                                                                }
                                                                            },
                                                                            Response.ErrorListener {
                                                                                Toast.makeText(this, "Error1: Please try again",
                                                                                        Toast.LENGTH_LONG).show()
                                                                            })
                                                                    Volley.newRequestQueue(this).add(jsonObjectRequest)
                                                                    try {
                                                                        Thread.sleep(5000)
                                                                    } catch (e: InterruptedException) {
                                                                        Thread.currentThread().interrupt()
                                                                    }
                                                                }
                                                            }catch (e: InterruptedException){
                                                                println("out of while")
                                                                Thread.currentThread().interrupt()
                                                            }
                                                        }).start()


                                                    } else if (noInputFlag == 1) {
                                                        Toast.makeText(this, "Error2: No Input Flag",
                                                                Toast.LENGTH_LONG).show()
                                                    }

                                        },
                                        Response.ErrorListener {
                                            Toast.makeText(this, "Error3: Please try again",
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
                                        Toast.makeText(this, "Error4: Please try again ",
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
