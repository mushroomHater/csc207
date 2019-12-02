package com.example.uoftlife.gamestudy;

import com.example.uoftlife.GameBaseActivity;
import com.example.uoftlife.R;

public class GameStudyActivity extends GameBaseActivity {
//
//    private GameStudy game;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        // setup the textView of the riddle and the button
//
//        game = new GameStudy(0);
//    }

    @Override
    protected int setContentLayout() {
        return R.layout.activity_game_study;
    }

    @Override
    protected boolean setSavable() {
        return false;
    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        setScorePrompt();
//        setRiddlePrompt();
//        setdoneBtn();
//        setHealthPrompt();
//    }
//
//
//    void setHealthPrompt() {
//        TextView prompt = findViewById(R.id.health);
//        prompt.setText("Your health left: " + (game.getHealth()));
//    }
//
//    /**
//     * set the textView of current riddle
//     */
//    void setRiddlePrompt() {
//        TextView prompt = findViewById(R.id.riddle);
//        prompt.setText(game.getCurrentRiddle());
//    }
//
//    /**
//     * set the textview of the score.
//     */
//    void setScorePrompt() {
//        TextView the_score = findViewById(R.id.the_score);
//        the_score.setText("Your score is " + game.getScore());
//    }
//
//    /**
//     * set up the guessing button
//     */
//    void setdoneBtn() {
//        Button doneBtn = findViewById(R.id.doneBtn);
//
//        doneBtn.setText("Guess!");
//        doneBtn.setOnClickListener(v -> {
//            EditText playerInput = findViewById(R.id.playerInput);
//            String answer = playerInput.getText().toString();
//            if (game.checkIfMatch(answer)) {
//                Toast.makeText(getApplicationContext(), "Bingo!", Toast.LENGTH_SHORT).show();
//                game.updateScore();
//            } else {
//                Toast.makeText(getApplicationContext(), "Wrong Answer, Right Answer is " + game.getAnswer(), Toast.LENGTH_SHORT)
//                        .show();
//            }
//            game.updateHealth();
//            setHealthPrompt();
//
//        });
//        setRiddlePrompt();
//        setScorePrompt();
//
//
//    }
//
}
