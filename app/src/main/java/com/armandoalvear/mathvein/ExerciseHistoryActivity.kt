package com.armandoalvear.mathvein

import android.app.ProgressDialog
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.*
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import org.w3c.dom.Text

class ExerciseHistoryActivity : AppCompatActivity() {
//   // lateinit var  eList: RecyclerView
//    lateinit var linearLayoutManager: LinearLayoutManager
//    lateinit var dividerItemDecoration: DividerItemDecoration
//    lateinit var exerciseList: List<Exercise>
//    lateinit var adapter: Adapter
//
//    val moviesList = new arrayListOf<Exercise>()
//    val adapter = new ExerciseAdapter(getApplicationContext(), exerciseList);
//    val eList = findViewById<RecyclerView>(R.id.list_ex_history)
//    exerciseList = arrayListOf<Int>()

    private lateinit var eList: RecyclerView

   // private lateinit var exerList: ArrayList<Int>()
    private val exerciseList = ArrayList<Exercise>()

    private lateinit var adapter: ExerciseAdapter
    //private val adapter = ExerciseAdapter(applicationContext, exerciseList)

    private lateinit var linearLayoutManager: LinearLayoutManager
    //private val linearLayoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false)

    private lateinit var dividerItemDecoration: DividerItemDecoration
    //private val dividerItemDecoration = DividerItemDecoration(eList.context, linearLayoutManager.orientation)

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

//        val listView = findViewById<ListView>(R.id.exerciseHistoryListView)
//        listView.adapter = MyCustomAdapter(this)
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

//    private class MyCustomAdapter(context: Context): BaseAdapter() {
//        val url = "https://aa1191.000webhostapp.com/scripts/exercise_history.php"
//        val userID = 1
//
//        val jsonObject = JSONObject().apply{
//            put("user_id", userID)
//        }
//        val jsonObjectRequest = JsonObjectRequest(Request.Method.POST, url, jsonObject,
//                Response.Listener {
//
//                },
//                Response.ErrorListener {
//
//                })
//        //Volley.newRequestQueue(this).add(jsonObjectRequest)
//
//        private val mContext: Context
//        init{
//            mContext = context
//        }
//        //responsible for how many rows in my list
//        override fun getCount(): Int {
//            return 5
//        }
//
//        //ignore
//        override fun getItemId(position: Int): Long {
//            return position.toLong()
//        }
//
//        //ignore
//        override fun getItem(position: Int): Any {
//            return "TEST STRING"
//        }
//
//        //Responsible for rendering out each row
//        override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup?): View {
//
//            val layoutInflater = LayoutInflater.from(mContext)
//            val rowExercise = layoutInflater.inflate(R.layout.row_exercise, viewGroup, false)
//            val description = rowExercise.findViewById<TextView>(R.id.description_ex_history)
//            val startTime = rowExercise.findViewById<TextView>(R.id.start_ex_history)
//            val endTime = rowExercise.findViewById<TextView>(R.id.end_ex_history)
//            val intensity = rowExercise.findViewById<TextView>(R.id.intensity_ex_history)
//            val date = rowExercise.findViewById<TextView>(R.id.date_ex_history)
//
//            val ampm = ""
//
//            description.text = ""
//            startTime.text = ""
//            endTime.text = ""
//            intensity.text = ""
//            date.text = ""
//            return rowExercise
//        }
//    }
}
