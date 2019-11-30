package com.example.uoftlife;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.util.DisplayMetrics;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class GameSleepActivity extends AppCompatActivity implements GameSleepView {

    //private GameConfiguration config = GameConfiguration.getConfig();
    /**
     * The countdown timer of the game level..
     */
    private CountDownTimer timer;

    /**
     * Indicates if the timer is running.
     */
    private boolean timing;

    /**
     * The time left in this game level in milliseconds.
     */
    private long timeLeftInMilliseconds = 16000; // 16 seconds

    /**
     * The text view of the timer.
     */
    Button alarmButton;
    private TextView timerText;

    /**
     * The time interval for the button to change position.
     */
    private int alarmChangePositionInterval = 4000;

    /**
     * The GameSleepPresenter instance passed into the game level.
     */
    private GameSleepPresenter gameSleepPresenter;


    /**
     * Specifies the activity lifecycle.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_sleep);
        ImageView gameSleepBackground = findViewById(R.id.gameSleepBG);
        GameSleepModel gameSleepModel = createGameSleepModel();
        gameSleepPresenter = new GameSleepPresenter(gameSleepModel, this);

        setInitialCharacter();
        setAlarmBtn();
        setTimer();
        setInitialLanguage();
        setConfigBtn();
        gameSleepPresenter.initializeDifficulty();

    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();
        timing = false;
        startTimer();
    }

    @Override
    protected void onResume() {
        super.onResume();
        timing = true;
        startTimer();
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        timing = true;
//        if (GameConfiguration.getConfig().getLanguage().equals("English")) {
//            ((Button) findViewById(R.id.btnWakeUp)).setText(R.string.wake_up);
//            ((Button) findViewById(R.id.gameconfig)).setText(R.string.gameconfig);
//        } else {
//            ((Button) findViewById(R.id.btnWakeUp)).setText(R.string.wake_up_cn);
//            ((Button) findViewById(R.id.gameconfig)).setText(R.string.gameconfig_cn);
//        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return keyCode == KeyEvent.KEYCODE_BACK;
    }

    GameSleepModel createGameSleepModel() {
        final DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        return new GameSleepModel(displaymetrics.widthPixels * 0.7f, displaymetrics.widthPixels * 0.7f);
    }

    /**
     * Sets the function of the alarm button.
     */
    private void setAlarmBtn() {
        alarmButton = findViewById(R.id.BtnAlarm);

        final Timer buttonTimer = new Timer();
        buttonTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(() -> {
                    gameSleepPresenter.handleAlarmAnimation();
                });
            }
        }, 4000, alarmChangePositionInterval);

        alarmButton.setOnClickListener((view) -> {
            gameSleepPresenter.addClickAmount();
            gameSleepPresenter.makeToast();
            handleCharacter();
        });
    }

    /**
     * Sets the configuration button on top of the game level
     */
    private void setConfigBtn() {
//        if (GameConfiguration.getConfig().getLanguage().equals("English")) {
        ((Button) findViewById(R.id.gameconfig)).setText(R.string.gameconfig);
//        } else {
//            ((Button) findViewById(R.id.gameconfig)).setText(R.string.gameconfig_cn);
//
//        }
//        findViewById(R.id.gameconfig).setOnClickListener((view) -> {
//            Intent i = new Intent(this, PauseDialogConfig.class);
//            i.putExtra("from", 'G');
//            startActivity(i);
//        });
    }

    /**
     * Sets the character's initial appearance in the game level.
     */
    private void setInitialCharacter() {
        findViewById(R.id.characterSit).setVisibility(View.INVISIBLE);
        findViewById(R.id.character1).setVisibility(View.VISIBLE);
        findViewById(R.id.character2).setVisibility(View.VISIBLE);
        findViewById(R.id.character3).setVisibility(View.VISIBLE);
        findViewById(R.id.character4).setVisibility(View.VISIBLE);
    }

    private void setInitialLanguage() {
//        if (GameConfiguration.getConfig().getLanguage().equals("English")) {
//        ((Button) findViewById(R.id.btnAlarm)).setText(R.string.wake_up);
//            Toast.makeText(getApplicationContext(), "Wake up Xiao Ming by tapping the button! ",
//                    Toast.LENGTH_LONG)
//                    .show();
//        } else {
//            ((Button) findViewById(R.id.btnWakeUp)).setText(R.string.wake_up_cn);
//            Toast.makeText(getApplicationContext(), "不断点击按钮让小明起床！",
//                    Toast.LENGTH_LONG)
//                    .show();
//        }
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
        if (timing) {
            timer = new CountDownTimer(timeLeftInMilliseconds, 1000) {
                @Override
                public void onTick(long l) {
                    timeLeftInMilliseconds = l;
                    updateTimer();
                }

                @Override
                public void onFinish() {
                    timer.cancel();
                }

            }.start();
        } else timer.cancel();
//        timing = true;
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
            //UserManager.getCurrentUser().setLevelScore(1, gameSleepModel.getScore());
            finish();
        }
    }

    /**
     * Shows the alarm and assign location
     */
    @Override
    public void showAlarmAnimation(float dx, float dy) {
        alarmButton.animate()
                .x(dx)
                .y(dy)
                .setDuration(0)
                .start();

    }

    /**
     * Shows the toast instruction.
     */
    @Override
    public void makeToast() {
        Toast.makeText(getApplicationContext(), "Keep tapping! ",
                Toast.LENGTH_LONG)
                .show();
    }

    /**
     * Handles the appearance of the character.
     */
    @Override
    public void handleCharacter() {
        int d = gameSleepPresenter.handleCharacter();
        switch (d) {
            // if the click amount reaches 1/4 of the target click, hide the first layer of character
            case 1:
                findViewById(R.id.character4).setVisibility(View.INVISIBLE);
                break;
            // if the click amount reaches 1/2 of the target click, hide the second layer of character
            case 2:
                findViewById(R.id.character3).setVisibility(View.INVISIBLE);
                break;
            // if the click amount reaches 3/4 of the target click, hide the third layer of character
            case 3:
                findViewById(R.id.character2).setVisibility(View.INVISIBLE);
                break;

            // if the click amount reaches the target click, hide the fourth layer
            // and show the final  layer of the character
            case 4:
                findViewById(R.id.character1).setVisibility(View.INVISIBLE);
                findViewById(R.id.characterSit).setVisibility(View.VISIBLE);
                break;
        }
    }


    /**
     * Shows the outcome of the game level after hiding the elements from display
     */
    private void showOutcome() {
        //hide the button and timer when time is up.
        findViewById(R.id.BtnAlarm).setVisibility(View.GONE);
        findViewById(R.id.levelOneCountDown).setVisibility(View.GONE);
        System.out.println("SCORE: " + gameSleepPresenter.getScore() + "/100");

    }
}

