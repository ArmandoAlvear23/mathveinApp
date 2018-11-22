package com.armandoalvear.mathvein

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.ListView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.util.ArrayList;

@Suppress("DEPRECATION")
class MealHistoryActivity : AppCompatActivity() {

    private lateinit var mList: RecyclerView
    private val mealList = ArrayList<Meal>()
    private lateinit var adapter: MealAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var dividerItemDecoration: DividerItemDecoration
    private val url = "https://aa1191.000webhostapp.com/scripts/meal_history.php"
    private val userID = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meal_history)


        mList = findViewById<RecyclerView>(R.id.mealsRecyclerView) as RecyclerView
        adapter = MealAdapter(applicationContext, mealList)
        linearLayoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false)
        dividerItemDecoration = DividerItemDecoration(mList.context, linearLayoutManager.orientation)

        mList.setHasFixedSize(true)
        mList.layoutManager = linearLayoutManager
        mList.addItemDecoration(dividerItemDecoration)
        mList.adapter = adapter
        getData()
    }
    private fun getData() {
        val progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Loading...")
        progressDialog.show()

        val jsonObject = JSONObject().apply{
            put("user_id", userID)
        }
        val jsonArray = JSONArray().apply{
            put(jsonObject)
        }

        val jsonArrayRequest = JsonArrayRequest(Request.Method.POST,url,jsonArray, Response.Listener<JSONArray> { response ->
            for (i in 0 until response.length()){
                try{
                    val jsonObject = response.getJSONObject(i)
                    val meal = Meal()
                    meal.mainDish = (jsonObject.getString("main"))
                    meal.appetizer = (jsonObject.getString("appetizer"))
                    meal.drink = (jsonObject.getString("drink"))
                    meal.dessert = (jsonObject.getString("dessert"))
                    meal.snack = (jsonObject.getString("snack"))
                    meal.timestamp = (jsonObject.getString("timestamp"))
                    mealList.add(meal)

                } catch (e: JSONException){
                    e.printStackTrace()
                    progressDialog.dismiss()
                }
            }
            adapter.notifyDataSetChanged()
            progressDialog.dismiss()
        }, Response.ErrorListener{error ->
            Log.e("Volley", error.toString())
            progressDialog.dismiss()
        })

        val requestQueue = Volley.newRequestQueue(this)
        requestQueue.add<JSONArray>(jsonArrayRequest)
    }

}
