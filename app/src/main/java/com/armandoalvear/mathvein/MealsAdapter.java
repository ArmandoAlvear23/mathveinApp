package com.armandoalvear.mathvein;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MealsAdapter extends RecyclerView.Adapter<MealsAdapter.MealViewHolder> {

    private Context mCtx;
    private List<Meal> mealList;

    public MealsAdapter(Context mCtx, List<Meal> mealList){
        this.mCtx = mCtx;
        this.mealList = mealList;
    }

    @Override
    public MealViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.meals_list, null);
        return new MealViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MealViewHolder holder, int position){
        Meal meal = mealList.get(position);

        holder.appetizerTextView.setText(meal.getAppetizer());
        holder.mainTextView.setText(meal.getMain());
        holder.dessertTextView.setText(meal.getDessert());
        holder.snackTextView.setText(meal.getSnack());
        holder.drinkTextView.setText(meal.getDrink());
    }

    @Override
    public int getItemCount(){
        return mealList.size();
    }

    class MealViewHolder extends RecyclerView.ViewHolder{
        TextView appetizerTextView, mainTextView, dessertTextView, snackTextView, drinkTextView;

        public MealViewHolder(View itemView){
            super(itemView);
            appetizerTextView = itemView.findViewById(R.id.appetizerTextView);
            mainTextView = itemView.findViewById(R.id.mainTextView);
            dessertTextView = itemView.findViewById(R.id.dessertTextView);
            snackTextView = itemView.findViewById(R.id.snackTextView);
            drinkTextView = itemView.findViewById(R.id.drinkTextView);
        }
    }
}
