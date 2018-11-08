package com.armandoalvear.mathvein

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import kotlinx.android.synthetic.main.activity_meal.*
import org.json.JSONObject

class MealActivity : AppCompatActivity() {

    lateinit var submitBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meal)

        submitBtn.setOnClickListener{

            val userID = 1
            val appetizer = appetizerField.text.toString()
            val main = mainField.text.toString()
            val dessert = dessertField.text.toString()
            val snack = snackField.text.toString()
            val drink = drinkField.text.toString()

            val jsonObject = JSONObject().apply{
                put("user_id", userID)
                put("appetizer", appetizer)
                put("main", main)
                put("dessert", dessert)
                put("snack", snack)
                put("drink", drink)
            }

            val url = "https://aa1191.000webhostapp.com/scripts/enter_meals.php"
            val jsonObjectRequest = JsonObjectRequest(Request.Method.POST, url, jsonObject,
                    Response.Listener{
                        val error = it.get("error")
                        if (error == 0){
                            appetizerField.setText("")
                            mainField.setText("")
                            dessertField.setText("")
                            snackField.setText("")
                            drinkField.setText("")

                            Toast.makeText(this, "Successful!",
                                    Toast.LENGTH_LONG).show()
                        }
                        else{
                            Toast.makeText(this,"Error:  $error",
                                    Toast.LENGTH_LONG).show()
                        }
                    },
                    Response.ErrorListener {
                        Toast.makeText(this, "Unsuccessful",
                                Toast.LENGTH_LONG).show()

                    })
        }
    }
}
