package com.example.stronk;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import java.lang.*;
import java.util.ArrayList;
import java.util.Map;


public class YTSaver extends AppCompatActivity {
    /*
        Variable Declarations
     */
    SharedPreferences sharedPreferences;
    RecyclerView recyclerView;
    YoutubeAdapter adapter;
    Button mButton;
    EditText mEdit;
    String url, saveName;
    Map<String, ?> allEntries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
          /*
            Boiler Plate Code for instantiating the activity's view,
            the appbar, and the "back" button in the appbar
         */
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_y_t_saver);

        Toolbar YoutubeToolbar = findViewById(R.id.toolbarYT);
        setSupportActionBar(YoutubeToolbar);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        /*
        * Get shared list of youtube links from storage.
        * */
        sharedPreferences = this.getSharedPreferences("Links", MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();

        /*
        * Store all of the unique keys for links for reference later
        * */
        allEntries = sharedPreferences.getAll();
        final ArrayList<String> keysArray = new ArrayList<String>();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            keysArray.add(entry.getKey());
        }

        /*
        * Setup RecyclerView and it's adapter
        * */
        recyclerView = findViewById(R.id.List);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new YoutubeAdapter(this, keysArray);
        recyclerView.setAdapter(adapter);

        /*
        * Setup save button and configure it to save to storage and error check input
        * */
        mButton = (Button)findViewById(R.id.saveButton);
        mButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                /*
                * Get user input from text boxes and save into readable string
                * */
                mEdit = (EditText)findViewById(R.id.editTextTextPersonName);
                url = mEdit.getText().toString();
                mEdit = (EditText)findViewById(R.id.SaveNameBox);
                saveName = mEdit.getText().toString();

                /*
                * Make sure save name is less than 24 characters for formatting reasons
                * */
                if (saveName.length() > 24)
                    Toast.makeText(getApplication(),"ERROR: Only 24 characters for save name", Toast.LENGTH_LONG).show();
                else {
                    /*
                    * Check if the URL is valid before saving
                    * */
                    if (URLUtil.isValidUrl(url)) {
                        keysArray.add(saveName);
                        editor.putString(saveName, url);
                        editor.apply();
                        adapter.notifyDataSetChanged();

                        Toast.makeText(getApplication(), saveName + " Saved.", Toast.LENGTH_LONG).show();
                    }
                    else {
                        Toast.makeText(getApplication(), "ERROR: Not valid URL. Please try again", Toast.LENGTH_LONG).show();
                    }
                }

            }
        });
    }
}