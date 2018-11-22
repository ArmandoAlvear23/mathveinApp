@file:Suppress("DEPRECATION")

package com.armandoalvear.mathvein

import android.app.ProgressDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class ExerciseHistoryActivity : AppCompatActivity() {

    private lateinit var eList: RecyclerView
    private val exerciseList = ArrayList<Exercise>()
    private lateinit var adapter: ExerciseAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var dividerItemDecoration: DividerItemDecoration
    private val url = "https://aa1191.000webhostapp.com/scripts/exercise_history.php"
    private val userID = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise_history)

        eList = findViewById<RecyclerView>(R.id.list_ex_history) as RecyclerView
        adapter = ExerciseAdapter(applicationContext, exerciseList)
        linearLayoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false)
        dividerItemDecoration = DividerItemDecoration(eList.context, linearLayoutManager.orientation)

        eList.setHasFixedSize(true)
        eList.layoutManager = linearLayoutManager
        eList.addItemDecoration(dividerItemDecoration)
        eList.adapter = adapter
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
                val exercise = Exercise()
                exercise.endTime = (jsonObject.getString("end_time"))
                exercise.endAmPm = (jsonObject.getInt("end_time_am_pm"))
                exercise.startTime = (jsonObject.getString("start_time"))
                exercise.startAmPm = (jsonObject.getInt("start_time_am_pm"))
                exercise.desc = (jsonObject.getString("description"))
                exercise.intense = (jsonObject.getInt("intensity"))
                exercise.timestamp = (jsonObject.getString("timestamp"))

                exerciseList.add(exercise)
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
