package com.example.uoftlife;


import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.concurrent.TimeUnit;
import java.lang.InterruptedException;
import java.lang.Exception;

import androidx.appcompat.app.AppCompatActivity;

public class LevelTwoPage extends AppCompatActivity{

    private int i;

    /**
     * the button to click when confirming the steps a player entered
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("fucl");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_level_two);
//        ImageView levelTwoView = findViewById(R.id.levelTwoView);

        okBtn();
        setPrompt();
    }

    private void setPrompt(){
        TextView prompt = (TextView)findViewById(R.id.step_AI);
        prompt.setText("You'are at step" + LevelTwo.curr_step);
    }

    public void getInputStep(){
        findViewById(R.id.okBtn).setOnClickListener(v -> {
            EditText setMoveStep = findViewById(R.id.setMoveStepTextView);
            int number = Integer.parseInt(setMoveStep.getText().toString());
        });
    }

    public void okBtn(){
        findViewById(R.id.okBtn).setOnClickListener(v -> {
            EditText setMoveStep = findViewById(R.id.setMoveStepTextView);
            int number = Integer.parseInt(setMoveStep.getText().toString());
            if (i % 2 == 0){
            if (number >= LevelTwo.get_min() && number <= LevelTwo.get_max()) {
                LevelTwo.addstep(number);
                TextView prompt = (TextView)findViewById(R.id.step_AI);
                prompt.setText("You'are at step" + LevelTwo.curr_step);

            } else {
                Toast.makeText(getApplicationContext(), "number not in range!", Toast.LENGTH_SHORT)
                        .show();
            }
            if (LevelTwo.curr_step > LevelTwo.get_goal()){
                TextView prompt = (TextView)findViewById(R.id.step_AI);
                prompt.setText("You win");
            }}

            else{
            LevelTwo.addstep(LevelTwo.AI_move());
            TextView prompt = (TextView)findViewById(R.id.step_AI);
            prompt.setText("AI is at step" + LevelTwo.curr_step);

            if (LevelTwo.curr_step > LevelTwo.get_goal()){
                TextView promp = (TextView)findViewById(R.id.step_AI);
                promp.setText("AI win");
            }}
            i++;

        });
        }

}

