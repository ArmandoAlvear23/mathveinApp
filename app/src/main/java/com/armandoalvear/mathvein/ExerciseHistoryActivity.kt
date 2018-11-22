package com.armandoalvear.mathvein

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import kotlinx.android.synthetic.*
import org.json.JSONObject
import org.w3c.dom.Text

class ExerciseHistoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise_history)

        val listView = findViewById<ListView>(R.id.exerciseHistoryListView)
        listView.adapter = MyCustomAdapter(this)
    }

    private class MyCustomAdapter(context: Context): BaseAdapter() {
        val url = "https://aa1191.000webhostapp.com/scripts/exercise_history.php"
        val userID = 1

        val jsonObject = JSONObject().apply{
            put("user_id", userID)
        }
        val jsonObjectRequest = JsonObjectRequest(Request.Method.POST, url, jsonObject,
                Response.Listener {

                },
                Response.ErrorListener {

                })
        //Volley.newRequestQueue(this).add(jsonObjectRequest)

        private val mContext: Context
        init{
            mContext = context
        }
        //responsible for how many rows in my list
        override fun getCount(): Int {
            return 5
        }

        //ignore
        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        //ignore
        override fun getItem(position: Int): Any {
            return "TEST STRING"
        }

        //Responsible for rendering out each row
        override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup?): View {

            val layoutInflater = LayoutInflater.from(mContext)
            val rowExercise = layoutInflater.inflate(R.layout.row_exercise, viewGroup, false)
            val description = rowExercise.findViewById<TextView>(R.id.description_ex_history)
            val startTime = rowExercise.findViewById<TextView>(R.id.start_ex_history)
            val endTime = rowExercise.findViewById<TextView>(R.id.end_ex_history)
            val intensity = rowExercise.findViewById<TextView>(R.id.intensity_ex_history)
            val date = rowExercise.findViewById<TextView>(R.id.date_ex_history)

            val ampm = ""

            description.text = ""
            startTime.text = ""
            endTime.text = ""
            intensity.text = ""
            date.text = ""
            return rowExercise
        }
    }
}
