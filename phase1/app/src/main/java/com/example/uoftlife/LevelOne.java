package com.example.uoftlife;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;


public class LevelOne extends AppCompatActivity {
    /**
     * The timer of the game level.
     */
    private CountDownTimer timer;

    /**
     * Indicates if the timer is running.
     */
    private boolean timing = true;

    /**
     * The time left in this game level in milliseconds.
     */
    private long timeLeftInMilliseconds = 16000; // 10 seconds

    /**
     * The number of clicks entered by the user.
     */

    private TextView timerText;


    private LevelOneState levelOneState = new LevelOneState();


    /**
     * Creates a LevelOne instance.
     */
    public LevelOne() {

    }

    /**
     * Specifies the activities.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_one);
        ImageView levelOneView = findViewById(R.id.levelOneView);

        setWakeUpBtn();
        setTimer();
        startTimer();

    }


    protected void onStart() {
        super.onStart();
        startTimer();
    }

    protected void onPause() {
        super.onPause();

    }

    private void setWakeUpBtn() {
        findViewById(R.id.btnWakeUp).setOnClickListener((view) -> {
            levelOneState.addClickAmount();
            System.out.println("ClickAmount:" + levelOneState.getClickAmount());
            if (levelOneState.getClickAmount() == 1) {
                Toast.makeText(getApplicationContext(), "Keep Tapping!", Toast.LENGTH_SHORT)
                        .show();
            }
        });
    }

    private void setTimer() {
        timerText = findViewById(R.id.levelOneCountDown);
    }

    /**
     * Starts the countdown timer.
     */
    private void startTimer() {
        timer = new CountDownTimer(timeLeftInMilliseconds, 1000) {
            @Override
            public void onTick(long l) {
                timeLeftInMilliseconds = l;
                updateTimer();

            }

            @Override
            public void onFinish() {

            }

        }.start();

        timing = true;
    }

    /**
     * Stops the countdown timer.
     */
    private void stopTimer() {
        timer.cancel();
        timing = false;
    }

    /**
     * Updates the countdown timer.
     */
    private void updateTimer() {
        int seconds = (int) timeLeftInMilliseconds / 1000;

        String timeLeftText;
        timeLeftText = "00:";
        if (seconds < 10) {
            timeLeftText += "0";
        }
        timeLeftText += seconds;

        timerText.setText(timeLeftText);

        if (seconds == 0) {
            stopTimer();
            showOutcome();
            timing = false;
            System.out.println("Score:" + levelOneState.getScore() + "/100");
        }
    }


    /**
     * Shows the outcome of the game level after hiding the elements from display
     */
    private void showOutcome() {
        //hide the button and timer when time is up.
        findViewById(R.id.btnWakeUp).setVisibility(View.GONE);
        findViewById(R.id.levelOneCountDown).setVisibility(View.GONE);


    }
}
