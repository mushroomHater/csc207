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
        initialize();
    }


    @Override
    protected void onResume() {
        super.onResume();
        determineLanguage();
        level += 1;
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

            //todo
        }
    }

    private void initialize() {
        if(level==0){
        //startActivity(new Intent(this, LevelOne.class));
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
