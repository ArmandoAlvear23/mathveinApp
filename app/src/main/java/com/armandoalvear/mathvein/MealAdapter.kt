package com.armandoalvear.mathvein

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class MealAdapter(private val context: Context, private val list: List<Meal>): RecyclerView.Adapter<MealAdapter.ViewHolder>(){
    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.row_meal, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val meal = list[position]
        var mainDishValue = meal.mainDish
        var appetizerValue = meal.appetizer
        var dessertValue = meal.dessert
        var drinkValue = meal.drink
        var snackValue = meal.snack
        var dateValue = meal.timestamp.toString().substringBefore(' ')

        if (mainDishValue.isNullOrBlank()){
            mainDishValue = "(none specified)"
        }
        val finalAppetizer = "Appetizer: $appetizerValue"
        val finalDessert = "Dessert: $dessertValue"
        val finalDrink = "Drink: $drinkValue"
        val finalSnack = "Snack: $snackValue"
        val finalDate = "Date: $dateValue"

        holder.mealMain.setText(mainDishValue)
        holder.mealAppetizer.setText(finalAppetizer)
        holder.mealDessert.setText(finalDessert)
        holder.mealDrink.setText(finalDrink)
        holder.mealSnack.setText(finalSnack)
        holder.mealDate.setText(finalDate)

    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mealMain: TextView
        var mealAppetizer: TextView
        var mealDessert: TextView
        var mealDrink: TextView
        var mealSnack: TextView
        var mealDate: TextView

        init {
            mealMain = itemView.findViewById(R.id.main_me_history)
            mealAppetizer = itemView.findViewById(R.id.appetizer_me_history)
            mealDessert = itemView.findViewById(R.id.dessert_me_history)
            mealDrink = itemView.findViewById(R.id.drink_me_history)
            mealSnack = itemView.findViewById(R.id.snack_me_history)
            mealDate = itemView.findViewById(R.id.date_me_history)
        }
    }

}