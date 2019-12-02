package com.example.uoftlife.gamestudy;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.uoftlife.GameBaseActivity;
import com.example.uoftlife.R;
import com.example.uoftlife.util.TransitionPageBuilder;

public class GameStudyActivity extends GameBaseActivity {

    private GameStudy gameStudy;
    private long time;
    private CountDownTimer totalTimer;

//    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // setup variable

        gameStudy = new GameStudy();
        time = gameStudy.getTime();
    }

    @Override
    protected int setContentLayout() {
        return R.layout.activity_game_study;
    }

    @Override
    protected boolean setSavable() {
        return false;
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Setup texiview and button
        setScorePrompt();
        setdoneBtn();
        setWordPromp();

        //setup timer
        totalTimer = new CountDownTimer(time, 1000) {
            @Override
            public void onTick(long l) {
                setTimePromp((int) l/1000);
            }

            @Override
            public void onFinish() {
                end();
                finish();
            }
        }.start();
    }

    /**
     * end the game
     */
    private void end() {
        new TransitionPageBuilder(this).setTitle("Congratulations!!")
                .setDescription("You just finished your course!!")
                .setShowingTime(3)
                .addValueChange("practice", (int) Math.floor(gameStudy.getScore() / 5))
                .addValueChange("understanding", (int) Math.floor(gameStudy.getScore() / 5))
                .addValueChange("time", -12)
                .addValueChange("vitality", -gameStudy.getVitalityConsume())
                .start();
        totalTimer.cancel();
    }

    private void setWordPromp() {
        TextView promp = findViewById(R.id.word);
        promp.setText(gameStudy.randomGenerateWord());
    }

    private void setTimePromp(int timeleft) {
        TextView prompt = findViewById(R.id.totalTime);
        prompt.setText("The time left is " + timeleft);
    }


    /**
     * set the textview of the score.
     */
    void setScorePrompt() {
        TextView the_score = findViewById(R.id.the_score);
        the_score.setText("Your score is " + gameStudy.getScore());
    }
//
    /**
     * set up the guessing button
     */
    void setdoneBtn() {
        Button doneBtn = findViewById(R.id.doneBtn);

        doneBtn.setText("Confirm!");
        doneBtn.setOnClickListener(v -> {
            EditText playerInput = findViewById(R.id.playerInput);
            String answer = playerInput.getText().toString();
            gameStudy.updateScore(answer);
            setWordPromp();
            setScorePrompt();
        });

    }
}
