package com.example.stronk;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.drm.DrmStore;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Journal extends AppCompatActivity {
    /*
        Variable Declarations
     */
    EditText JournalText1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /*
            Boiler Plate Code for instantiating the activity's view,
            the appbar, and the "back" button in the appbar
         */
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal);

        Toolbar journalToolbar = findViewById(R.id.toolbar_journal);
        setSupportActionBar(journalToolbar);

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }

        /*
            Create the save button
         */
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floatingActionButton1);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Save("Note1.txt");
            }
        });

        /*
            Automatically open "Note1.txt"
         */
        JournalText1 = (EditText) findViewById(R.id.EditText1);
        JournalText1.setText(Open("Note1.txt"));
    }

    /*
        Function that saves the contents of the EditText field
        into file "fileName"
     */
    public void Save(String fileName) {
        try {
            OutputStreamWriter out =
                    new OutputStreamWriter(openFileOutput(fileName, 0));
            out.write(JournalText1.getText().toString());
            out.close();
            Toast.makeText(this, "Note Saved!", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            Toast.makeText(this, "Exception: " + e.toString(), Toast.LENGTH_LONG).show();
            //e.printStackTrace();
        } catch (IOException e) {
            Toast.makeText(this, "Exception: " + e.toString(), Toast.LENGTH_LONG).show();
            //e.printStackTrace();
        }
    }

    /*
        Boolean to determine whether the requested file exists
     */
    public boolean FileExist(String fname) {
        File file = getBaseContext().getFileStreamPath(fname);
        return file.exists();
    }

    /*
        Function that opens the requested filename and returns
        its contents as a string
     */
    public String Open(String fileName) {
        String content = "";
        if (FileExist(fileName)) {
            try {
                InputStream in = openFileInput(fileName);
                if (in != null) {
                    InputStreamReader tmp = new InputStreamReader(in);
                    BufferedReader reader = new BufferedReader(tmp);
                    String str;
                    StringBuilder buf = new StringBuilder();
                    while ((str = reader.readLine()) != null) {
                        buf.append(str + "\n");
                    } in .close();
                    content = buf.toString();
                } else {
                    Toast.makeText(this, content, Toast.LENGTH_LONG).show();
                }
            } catch (java.io.FileNotFoundException e) {} catch (Throwable t) {
                Toast.makeText(this, "Exception: " + t.toString(), Toast.LENGTH_LONG).show();
            }
        }
        return content;
    }
}