package com.example.uoftlife;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.TextView;

public class StageBetweenGames extends AppCompatActivity {

    private int level = -1;
    private boolean passed = false;

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
        if (level == 0) {
            launchLevel(1);
        } else {
            passed = UserManager.getCurrentUser().getLevelScore(level) != 0;
        }
        determineLanguage();
        setNextButton();
        setMessage();
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
        } else {

            ((TextView) findViewById(R.id.totalText)).setText(R.string.total_score_cn);
            ((TextView) findViewById(R.id.levelText)).setText(R.string.single_score_cn);
            ((Button) findViewById(R.id.config)).setText(R.string.setting_cn);
            if (level > 0 && UserManager.getCurrentUser().getLevelScore(level) == 0) {
                ((Button) findViewById(R.id.next)).setText(R.string.try_again_cn);
            } else {
                ((Button) findViewById(R.id.next)).setText(R.string.yes_cn);
            }
        }
    }


    private void setConfigButton() {
        findViewById(R.id.config).setOnClickListener((view) -> {
            Intent i = new Intent(this, PauseDialogConfig.class);
            i.putExtra("from", 'G');
            level -= 1;
            startActivity(i);
        });
    }

    private void launchLevel(int level) {
        if (level == 1) {
            startActivity(new Intent(this, LevelOne.class));

        } else if (level == 2) {
            startActivity(new Intent(this, LevelTwoPage.class));
        } else if (level == 3) {
            startActivity(new Intent(this, GameLevelThree.class));
        } else if (level > 3) {
            finish();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return false;
    }
    private void setMessage() {
        ((TextView) findViewById(R.id.totalScore)).setText(String.valueOf(UserManager.getCurrentUser().getTotalScore()));
        ((TextView) findViewById(R.id.levelScore)).setText(String.valueOf(UserManager.getCurrentUser().getLevelScore(level)));

        if (passed) {
            if (GameConfiguration.getConfig().getLanguage().equals("English")) {
                ((TextView) findViewById(R.id.message)).setText(R.string.win);
                ((Button) findViewById(R.id.next)).setText(R.string.yes);
            } else {
                ((TextView) findViewById(R.id.message)).setText(R.string.win_cn);
                ((Button) findViewById(R.id.next)).setText(R.string.yes_cn);
            }
        } else {
            if (GameConfiguration.getConfig().getLanguage().equals("English")) {
                ((TextView) findViewById(R.id.message)).setText(R.string.fail);
                ((Button) findViewById(R.id.next)).setText(R.string.try_again);
            } else {
                ((TextView) findViewById(R.id.message)).setText(R.string.fail_cn);
                ((Button) findViewById(R.id.next)).setText(R.string.try_again_cn);
            }
        }
    }

    private void setNextButton() {
        findViewById(R.id.next).setOnClickListener((view) -> {
            if (!passed) {
                level -= 1;
            }
            launchLevel(level + 1);
        });
    }


}
