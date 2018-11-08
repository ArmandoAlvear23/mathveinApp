package com.armandoalvear.mathvein

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_vitals.*
import org.json.JSONObject

class VitalsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vitals)

        submitBtn.setOnClickListener{
            val userID = 1
            val bloodGlucose = bloodSugarField.text.toString()
            val systolic = systolicField.text.toString()
            val diastolic = diastolicField.text.toString()

            val jsonObject = JSONObject().apply{
                put("user_id", userID)
                put("systolic", systolic)
                put("diastolic", diastolic)
                put("blood_glucose", bloodGlucose)
            }

            val url = "https://aa1191.000webhostapp.com/scripts/vitals.php"
            val jsonObjectRequest = JsonObjectRequest(Request.Method.POST, url, jsonObject,
                    Response.Listener {
                        val success = it.get("success")
                        if (success == 1){
                            bloodSugarField.setText("")
                            systolicField.setText("")
                            diastolicField.setText("")
                            Toast.makeText(this, "Updated!",
                                    Toast.LENGTH_LONG).show()
                        }
                        else {
                            val error = it.get("error")
                            Toast.makeText(this, "Error: $error",
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
