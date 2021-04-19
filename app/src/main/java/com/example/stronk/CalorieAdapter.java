package com.example.stronk;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

public class CalorieAdapter extends RecyclerView.Adapter<CalorieAdapter.ViewHolder>{
    /*
        Variable Declarations
     */
    Context context;
    ArrayList<String> keys;
    Map<String, ?> allEntries;

    /* Constructor for Calorie Recyclerview adapter */
    public CalorieAdapter(Context context, ArrayList<String> keys) {
        this.context = context;
        this.keys = keys;
    }

    /* Sets the correct card view for the recyclerview */
    @Override
    public CalorieAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.link_card, parent, false);
        return new ViewHolder(view);
    }

    /* Sets the correct meal name and calories into the recycler view */
    @Override
    public void onBindViewHolder(CalorieAdapter.ViewHolder holder, int position) {
        SharedPreferences sp = context.getSharedPreferences("Calories", MODE_PRIVATE);
        final SharedPreferences.Editor editor = sp.edit();
        allEntries = sp.getAll();

        Gson gson = new Gson();
        String json = (String) allEntries.get(keys.get(position));
        CalorieCounter.Meal m = gson.fromJson(json, CalorieCounter.Meal.class);

        holder.mealText.setText(m.mealName);
        holder.calText.setText(String.valueOf(m.Calories));
    }

    @Override
    public int getItemCount() {
        return keys.size();
    }

    /* Sets the Recyclerview to look at the right components */
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mealText;
        TextView calText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mealText = itemView.findViewById(R.id.MealName);
            calText = itemView.findViewById(R.id.CalorieText);
        }
    }
}
