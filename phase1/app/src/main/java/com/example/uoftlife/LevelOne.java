package com.example.uoftlife;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;


public class LevelOne extends AppCompatActivity {

    private GameConfiguration config = GameConfiguration.getConfig();
    /**
     * The countdown timer of the game level.
     */
    private CountDownTimer timer;

    /**
     * Indicates if the timer is running.
     */
    private boolean timing = true;

    /**
     * The time left in this game level in milliseconds.
     */
    private long timeLeftInMilliseconds = 1600; // 10 seconds

    /**
     * The text view of the timer.
     */
    private TextView timerText;

    /**
     * The levelOneState instance passed into the game level.
     */
    private LevelOneState levelOneState = new LevelOneState();

    /**
     * Creates a LevelOne instance.
     */
    public LevelOne() {

    }

    /**
     * Specifies the activity lifecycle.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_one);
        ImageView levelOneView = findViewById(R.id.levelOneView);

        setWakeUpBtn();
        setTimer();
        setXiaoMing();
        startTimer();
        setInitialLanguage();

    }

    @Override
    protected void onStart() {
        super.onStart();
        startTimer();
    }

    @Override
    protected void onPause() {
        super.onPause();
        timing = false;

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        timing = true;
        if (GameConfiguration.getConfig().getLanguage().equals("English")) {
            ((Button) findViewById(R.id.btnWakeUp)).setText(R.string.wake_up);
        } else {
            ((Button) findViewById(R.id.btnWakeUp)).setText(R.string.wake_up_cn);
        }

    }

    /**
     * Sets the function of the wake up button.
     */
    private void setWakeUpBtn() {
        findViewById(R.id.btnWakeUp).setOnClickListener((view) -> {
            levelOneState.addClickAmount();
            System.out.println("ClickAmount:" + levelOneState.getClickAmount());
            if (levelOneState.getClickAmount() == 4) {
                if (GameConfiguration.getConfig().getLanguage().equals("English")) {
                    Toast.makeText(getApplicationContext(), "Keep tapping! ",
                            Toast.LENGTH_LONG)
                            .show();
                } else {
                    Toast.makeText(getApplicationContext(), "继续点击！",
                            Toast.LENGTH_LONG)
                            .show();
                }
            }
            /* Specifies XiaoMing's appearance depending on the number of clicks entered. */
            if (levelOneState.getClickAmount() == LevelOneState.getTARGETCLICK() / 4) {
                findViewById(R.id.xiaoming4).setVisibility(View.INVISIBLE);
            }
            if (levelOneState.getClickAmount() == LevelOneState.getTARGETCLICK() / 2) {
                findViewById(R.id.xiaoming3).setVisibility(View.INVISIBLE);
            }

            if (levelOneState.getClickAmount() == LevelOneState.getTARGETCLICK() / 4 * 3) {
                findViewById(R.id.xiaoming2).setVisibility(View.INVISIBLE);
            }

            if (levelOneState.getClickAmount() == LevelOneState.getTARGETCLICK()) {
                findViewById(R.id.xiaoming1l).setVisibility(View.INVISIBLE);
                findViewById(R.id.xiaoming1).setVisibility(View.VISIBLE);
            }
        });
    }

    /**
     * Sets Xiao Ming's appearance in the game level.
     */
    private void setXiaoMing() {
        findViewById(R.id.xiaoming1).setVisibility(View.INVISIBLE);
        findViewById(R.id.xiaoming1l).setVisibility(View.VISIBLE);
        findViewById(R.id.xiaoming2).setVisibility(View.VISIBLE);
        findViewById(R.id.xiaoming3).setVisibility(View.VISIBLE);
        findViewById(R.id.xiaoming4).setVisibility(View.VISIBLE);
    }

    private void setInitialLanguage(){
        if (GameConfiguration.getConfig().getLanguage().equals("English")) {
            ((Button) findViewById(R.id.btnWakeUp)).setText(R.string.wake_up);
            Toast.makeText(getApplicationContext(), "Wake up Xiao Ming by tapping the button! ",
                    Toast.LENGTH_LONG)
                    .show();
        } else {
            ((Button) findViewById(R.id.btnWakeUp)).setText(R.string.wake_up_cn);
            Toast.makeText(getApplicationContext(), "不断点击按钮让小明起床！",
                    Toast.LENGTH_LONG)
                    .show();
        }
    }

    /**
     * Sets the countdown timer.
     */
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
                if (timing) {
                    updateTimer();
                }
            }

            @Override
            public void onFinish() {
                timer.cancel();
            }

        }.start();

        timing = true;
    }

    /**
     * Stops the countdown timer.
     */
    private void stopTimer() {
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
            UserManager.getCurrentUser().setLevelScore(1, levelOneState.getScore());
            finish();
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

