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
import java.util.ArrayList

@Suppress("DEPRECATION")
class VitalsHistoryActivity : AppCompatActivity() {

    private lateinit var vList: RecyclerView
    private val vitalsList = ArrayList<Vitals>()
    private lateinit var adapter: VitalsAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var dividerItemDecoration: DividerItemDecoration
    private val url = "https://aa1191.000webhostapp.com/scripts/vitals_history.php"
    private val userID = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vitals_history)

        vList = findViewById<RecyclerView>(R.id.vitalsRecyclerView) as RecyclerView
        adapter = VitalsAdapter(applicationContext, vitalsList)
        linearLayoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false)
        dividerItemDecoration = DividerItemDecoration(vList.context, linearLayoutManager.orientation)

        vList.setHasFixedSize(true)
        vList.layoutManager = linearLayoutManager
        vList.addItemDecoration(dividerItemDecoration)
        vList.adapter = adapter
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
                    val vitals = Vitals()
                    vitals.systolic = (jsonObject.getString("systolic"))
                    vitals.diastolic = (jsonObject.getString("diastolic"))
                    vitals.blood_sugar = (jsonObject.getString("blood_glucose"))
                    vitals.timestamp = (jsonObject.getString("timestamp"))
                    vitalsList.add(vitals)

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
