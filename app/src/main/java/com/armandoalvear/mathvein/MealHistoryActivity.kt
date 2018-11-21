package com.armandoalvear.mathvein

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.ListView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject
import java.util.ArrayList;

class MealHistoryActivity : AppCompatActivity() {

    private lateinit var mealList: MutableList<Meal>
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meal_history)

        recyclerView = findViewById(R.id.mealsRecyclerView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)

        mealList = ArrayList<Meal>()

        loadMeals()
    }

    private fun loadMeals(){
        val userID = 1
        val jsonObject = JSONObject().apply{
            put("user_id", userID)
        }
        val url = "https://aa1191.000webhostapp.com/scripts/meals_history.php"
        val jsonObjectRequest = JsonObjectRequest(Request.Method.POST, url, jsonObject,
                Response.Listener {
                    Toast.makeText(this, "Inside!",
                            Toast.LENGTH_LONG).show()
                    val error = it.get("error")
                    if(error==0){
                        val array = JSONArray(it)
                        for (i in 0 until array.length()){
                            val meal = array.getJSONObject(i)

                            mealList.add(Meal(
                                    meal.getString("appetizer"),
                                    meal.getString("main"),
                                    meal.getString("dessert"),
                                    meal.getString("snack"),
                                    meal.getString("drink"),
                                    meal.getString("timestamp")
                            ))
                        }
                        val adapter = MealsAdapter(this@MealHistoryActivity, mealList)
                        recyclerView.adapter = adapter
                    }
                },
                Response.ErrorListener {
                    Toast.makeText(this, "Unsuccessful!",
                            Toast.LENGTH_LONG).show()
                })
        Volley.newRequestQueue(this).add(jsonObjectRequest)
    }
}
