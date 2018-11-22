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

class WorkHistoryActivity : AppCompatActivity() {
    private lateinit var wList: RecyclerView
    private val workList = ArrayList<Work>()
    private lateinit var adapter: WorkAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var dividerItemDecoration: DividerItemDecoration
    private val url = "https://aa1191.000webhostapp.com/scripts/work_history.php"
    private val userID = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work_history)

        wList = findViewById<RecyclerView>(R.id.workRecyclerView) as RecyclerView
        adapter = WorkAdapter(applicationContext, workList)
        linearLayoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false)
        dividerItemDecoration = DividerItemDecoration(wList.context, linearLayoutManager.orientation)

        wList.setHasFixedSize(true)
        wList.layoutManager = linearLayoutManager
        wList.addItemDecoration(dividerItemDecoration)
        wList.adapter = adapter
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
                    val work = Work()
                    work.endTime = (jsonObject.getString("end_time"))
                    work.endAmPm = (jsonObject.getInt("end_time_am_pm"))
                    work.startTime = (jsonObject.getString("start_time"))
                    work.startAmPm = (jsonObject.getInt("start_time_am_pm"))
                    work.desc = (jsonObject.getString("description"))
                    work.intense = (jsonObject.getInt("intensity"))
                    work.timestamp = (jsonObject.getString("timestamp"))

                    workList.add(work)
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
