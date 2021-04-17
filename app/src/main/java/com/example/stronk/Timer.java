package com.example.stronk;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Timer extends AppCompatActivity {
    /*
        Variable Declarations
     */
    TextView timerTextView;
    EditText repsEditText;
    EditText minutesEditText;
    EditText secondsEditText;
    Button startTimerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /*
            Boiler Plate Code for instantiating the activity's view,
            the appbar, and the "back" button in the appbar
         */
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        Toolbar timerToolbar = findViewById(R.id.toolbar_journal2);
        setSupportActionBar(timerToolbar);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        /*
            Initialize variables to their component counterparts
         */
        timerTextView = findViewById(R.id.textViewTimer);
        repsEditText = findViewById(R.id.editTextReps);
        minutesEditText = findViewById(R.id.editTextMinutes);
        secondsEditText = findViewById(R.id.editTextSeonds);
        startTimerButton = findViewById(R.id.startTimerButton);
        final boolean[] allFieldsChecked = {true};

        /*
            Create the timer
         */
        timerTextView.setText("00:00:00");
        startTimerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                    Determine whether the user inputs are valid
                 */
                allFieldsChecked[0] = checkFieldsEmpty();
                if (allFieldsChecked[0]){
                    /*
                        Set values for reps, minutes, seconds, and milliseconds
                     */
                    final int reps = Integer.parseInt(repsEditText.getText().toString());
                    final int minutes = Integer.parseInt(minutesEditText.getText().toString());
                    final int seconds = Integer.parseInt(secondsEditText.getText().toString());
                    final int millisecondsTotal = (minutes * 60000) + (seconds * 1000);

                    /*
                        Start the timer and have it run "reps" number of times
                     */
                    new CountDownTimer(millisecondsTotal,1000) {
                        int i = 0;
                        @Override
                        public void onTick(long millisUntilFinished) {
                            NumberFormat f = new DecimalFormat("00");
                            long hour = (millisUntilFinished / 3600000) % 24;
                            long min = (millisUntilFinished / 60000) % 60;
                            long sec = (millisUntilFinished / 1000) % 60;
                            timerTextView.setText(f.format(hour) + ":" + f.format(min) + ":" + f.format(sec));
                        }

                        @Override
                        public void onFinish() {
                            if (i < reps - 1) {
                                i = i + 1;
                                this.start();
                            } else {
                                this.cancel();
                            }
                        }
                    }.start();
                }

            }
        });
    }

    /*
        Checks whether the user-input fields are empty and / or valid
     */
    private boolean checkFieldsEmpty() {
        int reps, min, sec;

        if (repsEditText.length() == 0){
            repsEditText.setError("Please enter a positive number of at least 1");
            return false;
        } else {
            reps = Integer.parseInt(repsEditText.getText().toString());
            if (reps > 30 || reps < 1) {
                repsEditText.setError("Please enter a number between 1 and 30");
                return false;
            }
        }

        if (minutesEditText.length() == 0){
            minutesEditText.setError("Please enter a positive number between 0 and 59");
            return false;
        } else {
            min = Integer.parseInt(minutesEditText.getText().toString());
            if (min > 59) {
                minutesEditText.setError("Please enter a number between 0 and 59");
                return false;
            }
        }

        if (secondsEditText.length() == 0) {
            secondsEditText.setError("Please enter a positive number between 0 and 59");
            return false;
        } else {
            sec = Integer.parseInt(secondsEditText.getText().toString());
            if (sec > 59) {
                secondsEditText.setError("Please enter a number between 0 and 59");
                return false;
            }
        }

        return true;
    }

}