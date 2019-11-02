package com.example.uoftlife;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class GameLevelThree extends AppCompatActivity {

    private LevelThree game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_level_three);

        // setup the textView of the riddle and the button
        GameConfiguration config = new GameConfiguration((byte) 1, "English");
        game = new LevelThree(0, config);
        setScorePrompt();
        setRiddlePrompt();
        setdoneBtn();
        setHealthPrompt();
    }

    void setHealthPrompt() {
        TextView prompt = findViewById(R.id.health);
        prompt.setText("Your health left: " + String.valueOf(game.getHealth()));
    }

    /**
     * set the textView of current riddle
     */
    void setRiddlePrompt() {
        TextView prompt = findViewById(R.id.riddle);
        prompt.setText(game.getCurrentRiddle());
    }

    /**
     * set the textview of the score.
     */
    void setScorePrompt() {
        TextView the_score = findViewById(R.id.the_score);
        the_score.setText("Your score is " + String.valueOf(game.getScore()));
    }

    /**
     * set up the guessing button
     */
    void setdoneBtn() {
        findViewById(R.id.doneBtn).setOnClickListener(v -> {
            EditText playerInput = findViewById(R.id.playerInput);
            String answer = playerInput.getText().toString();
            if (game.checkIfMatch(answer)) {
                Toast.makeText(getApplicationContext(), "Bingo!", Toast.LENGTH_SHORT)
                        .show();
                game.updateScore();
            } else {
                Toast.makeText(getApplicationContext(), "Wrong Answer, Right Answer is " + game.getAnswer(), Toast.LENGTH_SHORT)
                        .show();
                game.updateHealth();
                setHealthPrompt();
                if(game.getHealth() == 0) onDestroy();
            }
            setRiddlePrompt();
            setScorePrompt();

        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        UserManager.getCurrentUser().setLevelScore(3, game.getScore());
    }
}
