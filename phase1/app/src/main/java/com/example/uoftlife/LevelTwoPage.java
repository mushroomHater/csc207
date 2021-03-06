package com.example.uoftlife;


import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;
import java.lang.InterruptedException;
import java.lang.Exception;

import androidx.appcompat.app.AppCompatActivity;

public class LevelTwoPage extends AppCompatActivity {

    private int i;

    /**
     * the button to click when confirming the steps a player entered
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_level_two);
        LevelTwo.curr_step = 0;
        okBtn();
        setConfigBtn();
        showCurrStep();
        showGoal();
        showInstruction();
    }
    /**
     * the textview of instuction of this level
     */
    private void showInstruction() {
        TextView instruction = (TextView) findViewById(R.id.Instruction);
        if (GameConfiguration.getConfig().getLanguage().equals("English")) {
            instruction.setText("XiaoMing and his neighbour XiaoGang are late for school, so they need to run. " +
                    "Each of them can take one step per time, \nThe minimum step is " + LevelTwo.get_min()
                    + "\nand the maximum steps is" + LevelTwo.get_max() + "steps.\n" + "The first one reached " +
                    LevelTwo.get_goal() + " steps wins! Control XiaoMing to compete with XiaoGang!" +
                    "٩(๑•̀ω•́๑)۶");
        }
        else instruction.setText("小明和他的邻居小刚在迟到的边缘疯狂试探，从家里到学校一共有"+LevelTwo.get_goal() + "步，" +
                "他们每次可以走" + LevelTwo.get_min() + "步，最多可以走" + LevelTwo.get_max() + "步。控制小明，冲就完事了！٩(๑•̀ω•́๑)۶");
    }

    /**
     * the textview of currentstep
     */
    private void showCurrStep() {
        TextView currstep = (TextView) findViewById(R.id.CurrStep);
        if (GameConfiguration.getConfig().getLanguage().equals("English")) {
            currstep.setText("You'are at step" + LevelTwo.curr_step);
        } else currstep.setText("你的步数是" + LevelTwo.curr_step);
    }

    /**
     * the target step of this level
     */
    private void showGoal() {
        TextView goal = (TextView) findViewById(R.id.Goal);
        if (GameConfiguration.getConfig().getLanguage().equals("English")) {
            goal.setText("Target Step :" + LevelTwo.get_goal());
        } else goal.setText("目标步数 :" + LevelTwo.get_goal());
    }

    /**
     * Sets the configuration button on top of the game level
     */
    private void setConfigBtn() {
        if (GameConfiguration.getConfig().getLanguage().equals("English")) {
            ((Button) findViewById(R.id.gameconfig)).setText(R.string.gameconfig);
        } else {
            ((Button) findViewById(R.id.gameconfig)).setText(R.string.gameconfig_cn);

        }
        findViewById(R.id.gameconfig).setOnClickListener((view) -> {
            Intent i = new Intent(this, PauseDialogConfig.class);
            i.putExtra("from", 'G');
            startActivity(i);
        });
    }

    /**
     * the button to click when confirming the steps a player entered
     */
    public void okBtn() {
        findViewById(R.id.okBtn).setOnClickListener(v -> {
            EditText setMoveStep = findViewById(R.id.setMoveStepTextView);
            if (setMoveStep.getText().toString().equals("")) {
                Toast.makeText(getApplicationContext(), "please enter a number", Toast.LENGTH_SHORT)
                        .show();
            } else {

                int number = Integer.parseInt(setMoveStep.getText().toString());
                if (i % 2 == 0) {
                    if (number >= LevelTwo.get_min() && number <= LevelTwo.get_max()) {
                        LevelTwo.addstep(number);
                        TextView prompt = (TextView) findViewById(R.id.CurrStep);
                        if (GameConfiguration.getConfig().getLanguage().equals("English")) {
                            prompt.setText("You'are at step" + LevelTwo.curr_step);
                        } else prompt.setText("你的当前步数是" + LevelTwo.curr_step);

                    } else {
                        Toast.makeText(getApplicationContext(), "number not in range!", Toast.LENGTH_SHORT)
                                .show();
                    }
                    if (LevelTwo.curr_step >= LevelTwo.get_goal()) {
                        TextView prompt = (TextView) findViewById(R.id.CurrStep);
                        if (GameConfiguration.getConfig().getLanguage().equals("English")) {
                            prompt.setText("You win! (* ॑ᐜ ॑*)");
                        } else prompt.setText("你赢啦 (* ॑ᐜ ॑*)");
                        UserManager.getCurrentUser().setLevelScore(2, 100);
                        finish();

                    }

                } else {
                    LevelTwo.addstep(LevelTwo.AI_move());
                    TextView prompt = (TextView) findViewById(R.id.CurrStep);
                    if (GameConfiguration.getConfig().getLanguage().equals("English")) {
                        prompt.setText("XiaoGang is at step" + LevelTwo.curr_step);
                    } else prompt.setText("小刚的当前步数是" + LevelTwo.curr_step);

                    if (LevelTwo.curr_step > LevelTwo.get_goal()) {
                        TextView promp = (TextView) findViewById(R.id.CurrStep);

                        if (GameConfiguration.getConfig().getLanguage().equals("English")) {
                            promp.setText("XiaoGang wins (｡•́︿•̀｡)");
                        } else {
                            promp.setText("小刚赢了 (｡•́︿•̀｡)");
                        }

                        UserManager.getCurrentUser().setLevelScore(2, 0);
                        finish();
                    }

                }
                i++;
            }
        });
    }

    protected void onDestroy() {
        super.onDestroy();
        UserManager.getCurrentUser().setLevelScore(2, LevelTwo.get_Score());
    }

    @Override
    /**
     * go back a stage
     */
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return false;
    }
}

