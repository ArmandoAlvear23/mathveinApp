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

@Suppress("DEPRECATION")
class SleepHistoryActivity : AppCompatActivity() {

    private lateinit var sList: RecyclerView
    private val sleepList = ArrayList<Sleep>()
    private lateinit var adapter: SleepAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var dividerItemDecoration: DividerItemDecoration
    private val url = "https://aa1191.000webhostapp.com/scripts/sleep_history.php"
    private val userID = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sleep_history)

        sList = findViewById<RecyclerView>(R.id.sleepRecyclerView) as RecyclerView
        adapter = SleepAdapter(applicationContext, sleepList)
        linearLayoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false)
        dividerItemDecoration = DividerItemDecoration(sList.context, linearLayoutManager.orientation)

        sList.setHasFixedSize(true)
        sList.layoutManager = linearLayoutManager
        sList.addItemDecoration(dividerItemDecoration)
        sList.adapter = adapter
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
                    val sleep = Sleep()
                    sleep.endTime = (jsonObject.getString("end_time"))
                    sleep.endAmPm = (jsonObject.getInt("end_time_am_pm"))
                    sleep.startTime = (jsonObject.getString("start_time"))
                    sleep.startAmPm = (jsonObject.getInt("start_time_am_pm"))
                    sleep.timestamp = (jsonObject.getString("timestamp"))

                    sleepList.add(sleep)
                } catch (e: JSONException){
                    e.printStackTrace()
                    progressDialog.dismiss()
                }
            }
            adapter.notifyDataSetChanged()
            progressDialog.dismiss()
        }, Response.ErrorListener{ error ->
            Log.e("Volley", error.toString())
            progressDialog.dismiss()
        })

        val requestQueue = Volley.newRequestQueue(this)
        requestQueue.add<JSONArray>(jsonArrayRequest)
    }
}
