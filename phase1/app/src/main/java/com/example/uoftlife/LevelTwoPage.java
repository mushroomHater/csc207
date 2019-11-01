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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_level_two);
//        ImageView levelTwoView = findViewById(R.id.levelTwoView);

        okBtn();
        showCurrStep();
        showGoal();
//        showStepLimit();
        showInstruction();
    }


//    private void showStepLimit() {
//        TextView steplimit = (TextView) findViewById(R.id.StepLimit);
//        steplimit.setText("minimum step is" + LevelTwo.get_min() + "maximum step is" + LevelTwo.get_max());
//    }

    private void showInstruction() {
        TextView instruction = (TextView) findViewById(R.id.Instruction);
        instruction.setText("XiaoMing and his neighbour XiaoGang are late for school, so they need to run. " +
                "Each of them can take one step per time, and the maximum is 2 steps. " +
                "The first one reached 20 steps wins! Control XiaoMing to compete with XiaoGang!" +
                "٩(๑•̀ω•́๑)۶");
    }

    private void showCurrStep(){
        TextView currstep = (TextView)findViewById(R.id.CurrStep);
        currstep.setText("You'are at step" + LevelTwo.curr_step);
    }

    private void showGoal(){
        TextView goal = (TextView)findViewById(R.id.Goal);
        goal.setText("Target Step :" + LevelTwo.get_goal());
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
                TextView prompt = (TextView)findViewById(R.id.CurrStep);
                prompt.setText("You'are at step" + LevelTwo.curr_step);

            } else {
                Toast.makeText(getApplicationContext(), "number not in range!", Toast.LENGTH_SHORT)
                        .show();
            }
            if (LevelTwo.curr_step > LevelTwo.get_goal()){
                TextView prompt = (TextView)findViewById(R.id.CurrStep);
                prompt.setText("You win! (* ॑ᐜ ॑*)");

            }}

            else{
            LevelTwo.addstep(LevelTwo.AI_move());
            TextView prompt = (TextView)findViewById(R.id.CurrStep);
            prompt.setText("XiaoGang is at step" + LevelTwo.curr_step);

            if (LevelTwo.curr_step > LevelTwo.get_goal()){
                TextView promp = (TextView)findViewById(R.id.CurrStep);
                promp.setText("XiaoGang wins (｡•́︿•̀｡)");

            }}
            i++;



        });
        }

}

