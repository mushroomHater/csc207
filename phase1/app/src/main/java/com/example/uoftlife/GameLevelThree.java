package com.example.uoftlife;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
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

        game = new LevelThree(0, GameConfiguration.getConfig());
        setScorePrompt();
        setRiddlePrompt();
        setdoneBtn();
        setHealthPrompt();
    }

    void setHealthPrompt() {
        TextView prompt = findViewById(R.id.health);
        if (game.getConfig().getLanguage().equals("English")) prompt.setText("Your health left: " + String.valueOf(game.getHealth()));
        else prompt.setText("你的生命值还有: " + String.valueOf(game.getHealth()));
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
        if (game.getConfig().getLanguage().equals("English")) the_score.setText("Your score is " + game.getScore());
        else the_score.setText("分数: " + String.valueOf(game.getScore()));
    }

    /**
     * set up the guessing button
     */
    void setdoneBtn() {
        Button doneBtn = findViewById(R.id.doneBtn);
        if (game.getConfig().getLanguage().equals("Chinese")) doneBtn.setText("确定");
        doneBtn.setOnClickListener(v -> {
            EditText playerInput = findViewById(R.id.playerInput);
            String answer = playerInput.getText().toString();
            if (game.checkIfMatch(answer)) {
                if (game.getConfig().getLanguage().equals("English")) {
                    Toast.makeText(getApplicationContext(), "Bingo!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "答对啦!", Toast.LENGTH_SHORT).show();
                }
                game.updateScore();
            } else {
                if (game.getConfig().getLanguage().equals("English")) {
                    Toast.makeText(getApplicationContext(), "Wrong Answer, Right Answer is " + game.getAnswer(), Toast.LENGTH_SHORT)
                            .show();
                } else {
                    Toast.makeText(getApplicationContext(), "答错了，正确答案是: " + game.getAnswer(), Toast.LENGTH_SHORT)
                            .show();
                }
                game.updateHealth();
                setHealthPrompt();

            }
            setRiddlePrompt();
            setScorePrompt();
            System.out.println("health is: " + game.getHealth());
            if(game.getHealth() <= 0){
                System.out.println("I'm in");
                onDestroy();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        UserManager.getCurrentUser().setLevelScore(3, game.getScore());
    }
}
