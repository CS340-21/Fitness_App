package com.example.stronk;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

public class CalorieCounter extends AppCompatActivity {
    /*
        Variable Declarations
     */
    SharedPreferences sharedPreferences;
    RecyclerView recyclerView;
    CalorieAdapter adapter;
    Button saveButton;
    Button resetButton;
    EditText mealName;
    EditText calorieText;
    int totalCalories = 0;
    TextView totalCal;
    String mealNameString, CalString;
    Map<String, ?> allEntries;

    /*
    * Custom class for meals to allow for unique names.
    * */
    class Meal {
        String mealName;
        int Calories;
        String key;

        public Meal(String n, int c) {
            mealName = n;
            Calories = c;
           byte[] array = new byte[7];
           new Random().nextBytes(array);
           key = new String(array, Charset.forName("UTF-8"));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
          /*
            Boiler Plate Code for instantiating the activity's view,
            the appbar, and the "back" button in the appbar
         */
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie_counter);

        Toolbar CalorieToolbar = findViewById(R.id.toolbarCal);
        setSupportActionBar(CalorieToolbar);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        /*
        * Get saved calorie list from storage
        */
        totalCal = (TextView)findViewById(R.id.CalorieCount);
        sharedPreferences = this.getSharedPreferences("Calories", MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();

        /*
        * Store all the keys for future reference and get total amount of calories in the list
        */
        allEntries = sharedPreferences.getAll();
        final ArrayList<String> mealArray = new ArrayList<String>();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            Gson gson = new Gson();
            String json = (String) allEntries.get(entry.getKey());
            Meal m = gson.fromJson(json, Meal.class);
            mealArray.add(entry.getKey());
            totalCalories += m.Calories;
        }
        totalCal.setText(String.valueOf(totalCalories));

        /*
        * Setup RecyclerView and it's adapter
        */
        recyclerView = findViewById(R.id.CalorieList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CalorieAdapter(this, mealArray);
        recyclerView.setAdapter(adapter);

        /*
        * Setup save button and configure it to save to storage and error check input
        */
        saveButton = (Button)findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                 * Get user input from text boxes and save into readable string
                 */
                mealName = (EditText)findViewById(R.id.Meal);
                mealNameString = mealName.getText().toString();
                calorieText = (EditText)findViewById(R.id.Calorie);
                CalString = calorieText.getText().toString();

                /* Make sure the save name isn't blank and the calories is greater than zero */
                if (CalString.equals("") || Integer.parseInt(CalString) <= 0) {
                    Toast.makeText(getApplication(),"ERROR: Calories must be greater than 0", Toast.LENGTH_LONG).show();
                }
                else {
                    /* Double check the name isn't blank. */
                    if (mealNameString.equals("")) {
                        Toast.makeText(getApplication(),"ERROR: Must input meal name", Toast.LENGTH_LONG).show();
                    }
                    else {
                        /* Save the user meal and calorie data and update the recycler view */
                        Meal tmp = new Meal(mealNameString, Integer.parseInt(CalString));
                        mealArray.add(tmp.key);

                        Gson gson = new Gson();
                        String json = gson.toJson(tmp);

                        editor.putString(tmp.key, json);
                        editor.apply();
                        adapter.notifyDataSetChanged();

                        totalCalories += Integer.parseInt(CalString);
                        totalCal.setText(String.valueOf(totalCalories));
                    }
                }
            }
        });

        /* Set the reset button to clear all stored data. */
        resetButton = (Button)findViewById(R.id.resetButton);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mealArray.clear();
                editor.clear();
                editor.apply();
                adapter.notifyDataSetChanged();
                totalCalories = 0;
                totalCal.setText("0");
            }
        });

    }
}