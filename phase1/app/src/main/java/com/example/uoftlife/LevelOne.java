package com.example.uoftlife;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;


public class LevelOne extends AppCompatActivity implements Terminable {

    /**
     * The target number of click to pass the game level.
     */
    private static int TARGETCLICK = 45;

    /**
     * The tag of the game level.
     */
    private static String TAG = "LevelOne";

    /**
     * The timer of the game level.
     */
    private CountDownTimer timer;

    /**
     * Indicates if the timer is running.
     */
    private boolean timing = true;

    /**
     * The timer text view of the game level.
     */
    private TextView timerText;

    /**
     * The time left in this game level in milliseconds.
     */
    private long timeLeftInMilliseconds = 16000; // 10 seconds

    /**
     * The number of clicks entered by the user.
     */
    private int clickAmount;

    /**
     * The GameConfiguration instance passed into the game level.
     */
    private GameConfiguration config;


    /**
     * The wake up button clicked by the user.
     */
    private Button btnWakeUp;

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

        btnWakeUp = findViewById(R.id.btnWakeUp);
        btnWakeUp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                clickAmount++;
                System.out.println("ClickAmount:" + clickAmount);
                if (clickAmount == 1) {
                    Toast.makeText(getApplicationContext(), "Keep Tapping!", Toast.LENGTH_SHORT)
                            .show();
                }
                //hide the button when time is up.
                if (!timing) {
                    btnWakeUp.setVisibility(View.GONE);
                }
            }
        });

        ImageView levelOneView = findViewById(R.id.levelOneView);
        timerText = findViewById(R.id.levelOneCountDown);
        startTimer();

    }


    /**
     * Starts the countdown timer.
     */
    public void startTimer() {
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
    public void stopTimer() {
        timer.cancel();
        timing = false;
    }

    /**
     * Updates the countdown timer.
     */
    public void updateTimer() {
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
            System.out.println("Score:" + getScore());
        }
    }

    /**
     * Shows the outcome of the game level after hidding the elements from display
     */
    void showOutcome() {
        //hide the button when time is up.
        btnWakeUp.setVisibility(View.GONE);
        timerText.setVisibility(View.GONE);


    }

    /**
     * @return the number of clicks a user has entered.
     */
    int getClickAmount() {
        return clickAmount;
    }

    /**
     * Sets the number of clicks.
     */
    void setClickAmount(int clickAmount) {
        this.clickAmount = clickAmount;
    }

    /**
     * @return the timer of the game level.
     */
    CountDownTimer getTimer() {
        return timer;
    }

    /**
     * Sets the timer of the game level.
     */
    void setTimer(CountDownTimer timer) {
        this.timer = timer;

    }

    /**
     * @return the score of the user within the range of 0 - 100.
     * If the user passes the game level, return a score within the range 1 - 100,
     * depending on the performance/ click amount of the user.
     * If the user fails the game level, return 0.
     */
    @Override
    public int getScore() {
        if (isPassed()) {
            if (clickAmount >= TARGETCLICK &&
                    clickAmount < TARGETCLICK * 1.2) {
                return 60;
            } else if (clickAmount >= TARGETCLICK * 1.2 &&
                    clickAmount < TARGETCLICK * 1.3) {
                return 70;
            } else if (clickAmount >= TARGETCLICK * 1.3 &&
                    clickAmount < TARGETCLICK * 1.4) {
                return 80;
            } else if (clickAmount >= TARGETCLICK * 1.4 &&
                    clickAmount < TARGETCLICK * 1.5) {
                return 90;
            } else if (clickAmount >= TARGETCLICK * 1.5) {
                return 100;
            }
        }
        return 0;
    }

    /**
     * Checks if the user passes the game level.
     *
     * @return true if the player passes the game level, otherwise return false.
     */
    @Override
    public boolean isPassed() {
        return this.getClickAmount() >= TARGETCLICK;
    }

    /**
     * Updates the game level.
     *
     * @return true if the game level ends, otherwise return false.
     */
    @Override
    public boolean update() {
        return false;
    }

    /**
     * Passes the configurations in GameConfiguration into the game level.
     * The classes that implement this interface should have a field to store it.
     */
    @Override
    public void initialize(GameConfiguration config) {
        this.config = config;
    }

    /**
     * Clears the data and objects displayed on the screen of the game level.
     * It will be called once the game ended.
     */
    @Override
    public void clear() {

    }


}
