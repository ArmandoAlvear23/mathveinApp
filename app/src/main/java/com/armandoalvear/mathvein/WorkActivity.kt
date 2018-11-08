package com.armandoalvear.mathvein

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.SwitchCompat
import android.widget.Button
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_work.*
import org.json.JSONObject

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
            val description = descriptionField.text.toString()
            val intensity = intensitySpinner.selectedItem.toString()
            val startTime = startTimeField.text.toString()
            val endTime = endTimeField.text.toString()
            val sw1Status = if(switch_1.isChecked) 1 else 0
            val sw2Status = if(switch_2.isChecked) 1 else 0

            val jsonObject = JSONObject().apply{
                put("user_id", userID)
                put("description", description)
                put("intensity", intensity)
                put("start_time", startTime)
                put("start_time_am_pm", sw1Status)
                put("end_time", endTime)
                put("end_time_am_pm", sw2Status)
            }

            val url = "https://aa1191.000webhostapp.com/scripts/enter_work.php"
            val jsonObjectRequest = JsonObjectRequest(Request.Method.POST, url, jsonObject,
                    Response.Listener{
                        val error = it.get("error")
                        if(error==0){
                            descriptionField.setText("")
                            intensitySpinner.setSelection(0)
                            startTimeField.setText("")
                            endTimeField.setText("")
                            switch_1.isChecked = false
                            switch_2.isChecked = false

                            Toast.makeText(this, "Updated!",
                                    Toast.LENGTH_LONG).show()
                        }
                        else if (error==2){
                            Toast.makeText(this, "Error: Blank Fields",
                                    Toast.LENGTH_LONG).show()
                        }
                    },
                    Response.ErrorListener {
                        Toast.makeText(this, "Unsuccessful!",
                                Toast.LENGTH_LONG).show()
                    })
            Volley.newRequestQueue(this).add(jsonObjectRequest)
        }
    }
}
