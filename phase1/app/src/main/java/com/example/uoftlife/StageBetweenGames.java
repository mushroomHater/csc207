package com.example.uoftlife;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class StageBetweenGames extends AppCompatActivity {

    private int level = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stage_between_games);
        setConfigButton();
    }


    @Override
    protected void onResume() {
        super.onResume();
        level += 1;
        if(level==0){
            startActivity(new Intent(this, LevelOne.class));
        }
        determineLanguage();

    }

    private void determineLanguage() {
        if (GameConfiguration.getConfig().getLanguage().equals("English")) {

            ((TextView) findViewById(R.id.totalText)).setText(R.string.total_score);
            ((TextView) findViewById(R.id.levelText)).setText(R.string.single_score);
            ((Button) findViewById(R.id.config)).setText(R.string.setting);
            if (level > 0 && UserManager.getCurrentUser().getLevelScore(level) == 0) {
                ((Button) findViewById(R.id.next)).setText(R.string.try_again);
            } else {
                ((Button) findViewById(R.id.next)).setText(R.string.yes);
            }
            //todo
        } else {

            ((TextView) findViewById(R.id.totalText)).setText(R.string.total_score_cn);
            ((TextView) findViewById(R.id.levelText)).setText(R.string.single_score_cn);
            ((Button) findViewById(R.id.config)).setText(R.string.setting_cn);
            if (level > 0 && UserManager.getCurrentUser().getLevelScore(level) == 0) {
                ((Button) findViewById(R.id.next)).setText(R.string.try_again_cn);
            } else {
                ((Button) findViewById(R.id.next)).setText(R.string.yes_cn);
            }
            //todo
        }
    }


    private void setConfigButton() {
        findViewById(R.id.config).setOnClickListener((view) -> {
            Intent i = new Intent(this, PauseDialogConfig.class);
            i.putExtra("from",'G');
        });
    }

    private void showScore() {


//        UserManager.getCurrentUser().getLevelScore()
    }
}
