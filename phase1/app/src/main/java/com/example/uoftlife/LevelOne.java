package com.example.uoftlife;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;

public class LevelOne extends AppCompatActivity implements Terminable {

    /**
     * The target number of click to pass the game level.
     */
    private static int TARGETCLICK;

    /**
     * The tag of the game level.
     */
    private static String TAG = "LevelOne";

    /**
     * The timer of the game level.
     */
    private Timer timer;

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

        btnWakeUp = (Button) findViewById(R.id.btnWakeUp);
        btnWakeUp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                clickAmount++;
                System.out.println(clickAmount);
                //Toast.makeText(getApplicationContext(), "Keep Tapping!", Toast.LENGTH_SHORT)
                //.show();
            }
        });
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
    Timer getTimer() {
        return timer;
    }

    /**
     * Sets the timer of the game level.
     */
    void setTimer(Timer timer) {
        this.timer = timer;

    }

    /**
     * @return the score of the user within the range of 0 - 100.
     * If the user passes the game level, return a score within the range 1 - 100.
     * If the user fails the game level, return 0.
     */
    @Override
    public int getScore() {
        if (isPassed()) {
            return 60;
        } else {
            return 0;
        }
    }

    /**
     * Checks if the user passes the game level.
     *
     * @return true if the player passes the game level, otherwise return false.
     */
    @Override
    public boolean isPassed() {
        return clickAmount >= TARGETCLICK;
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
