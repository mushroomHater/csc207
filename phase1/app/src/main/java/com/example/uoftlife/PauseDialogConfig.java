package com.example.uoftlife;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Button;


public class PauseDialogConfig extends AppCompatActivity {
    private boolean openInGame;
    private boolean ensure = false;
    private boolean isEnglish;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pause_dialog_config);
        openInGame = getIntent().getCharExtra("from", 'M') == 'G';
        initialize();
    }

    private void initialize() {
        setFloatWindow();
        setExitButton();
        setResumeButton();
        isEnglish = GameConfiguration.getConfig().getLanguage().equals("English");
        if(isEnglish){
            ((RadioButton)findViewById(R.id.english)).setChecked(true);
        }else{
            ((RadioButton)findViewById(R.id.chinese)).setChecked(true);
        }
        byte d = GameConfiguration.getConfig().getDifficulty();
        if (d == 1) {
            ((RadioButton) findViewById(R.id.easy)).setChecked(true);
        } else if (d == 2) {
            ((RadioButton) findViewById(R.id.medium)).setChecked(true);
        } else {
            ((RadioButton) findViewById(R.id.hard)).setChecked(true);
        }

        if (!openInGame) {
            (findViewById(R.id.exit)).setEnabled(false);
            setDifficulty();
            ((TextView) findViewById(R.id.p_title)).setText(R.string.config);
        } else {
            (findViewById(R.id.easy)).setEnabled(false);
            (findViewById(R.id.hard)).setEnabled(false);
            (findViewById(R.id.medium)).setEnabled(false);
            ((TextView) findViewById(R.id.p_title)).setText(R.string.pause);
            ((TextView) findViewById(R.id.setDifficulty)).setText(R.string.cannot_change_difficulty);
        }
        setLanguage();
        determineLanguage();

    }

    private void determineLanguage() {
        if (isEnglish) {
            if (!openInGame) {
                ((TextView) findViewById(R.id.p_title)).setText(R.string.config);
                ((TextView) findViewById(R.id.setDifficulty)).setText(R.string.difficulty);
            } else {
                ((TextView) findViewById(R.id.p_title)).setText(R.string.pause);
                ((TextView) findViewById(R.id.setDifficulty)).setText(R.string.cannot_change_difficulty);
            }
            ((TextView) findViewById(R.id.setLanguage)).setText(R.string.language);
            ((TextView) findViewById(R.id.resume)).setText(R.string.resume);
            ((TextView) findViewById(R.id.exit)).setText(R.string.exit);

            ((TextView) findViewById(R.id.easy)).setText(R.string.easy);
            ((TextView) findViewById(R.id.medium)).setText(R.string.medium);
            ((TextView) findViewById(R.id.hard)).setText(R.string.hard);

        } else {

            if (!openInGame) {
                ((TextView) findViewById(R.id.p_title)).setText(R.string.config_cn);
                ((TextView) findViewById(R.id.setDifficulty)).setText(R.string.difficulty_cn);
            } else {
                ((TextView) findViewById(R.id.p_title)).setText(R.string.pause_cn);
                ((TextView) findViewById(R.id.setDifficulty)).setText(R.string.cannot_change_difficulty_cn);
            }
            ((TextView) findViewById(R.id.setLanguage)).setText(R.string.language_cn);
            ((TextView) findViewById(R.id.resume)).setText(R.string.resume_cn);
            ((TextView) findViewById(R.id.exit)).setText(R.string.exit_cn);

            ((TextView) findViewById(R.id.easy)).setText(R.string.easy_cn);
            ((TextView) findViewById(R.id.medium)).setText(R.string.medium_cn);
            ((TextView) findViewById(R.id.hard)).setText(R.string.hard_cn);

        }
    }

    private void setFloatWindow() {
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.dimAmount = 0.4f;
        attributes.x = 0;
        attributes.y = 0;
        attributes.width = 1024;
        attributes.height = 1278;
        getWindow().setAttributes(attributes);
    }

    private void setDifficulty() {
        findViewById(R.id.easy).setOnClickListener((view) -> {
            GameConfiguration.getConfig().setDifficulty((byte) 1);
        });
        findViewById(R.id.medium).setOnClickListener((view) -> {
            GameConfiguration.getConfig().setDifficulty((byte) 2);
        });
        findViewById(R.id.hard).setOnClickListener((view) -> {
            GameConfiguration.getConfig().setDifficulty((byte) 3);
        });
    }

    private void setLanguage() {
        findViewById(R.id.chinese).setOnClickListener((view) -> {
            GameConfiguration.getConfig().setLanguage("Chinese");
            isEnglish = false;
            determineLanguage();
        });
        findViewById(R.id.english).setOnClickListener((view) -> {
            GameConfiguration.getConfig().setLanguage("English");
            isEnglish = true;
            determineLanguage();
        });
    }

    private void setExitButton() {
        findViewById(R.id.exit).setOnClickListener((view) -> {
            if (openInGame) {
                if (!ensure) {
                    if (isEnglish) {
                        ((TextView) findViewById(R.id.p_title)).setText(R.string.exit_alert);
                    } else {
                        ((TextView) findViewById(R.id.p_title)).setText(R.string.exit_alert_cn);
                    }
                    ensure = true;
                } else {
                    startActivity(new Intent(this, MainActivity.class));
                }
            }
        });
    }

    private void setResumeButton() {
        findViewById(R.id.resume).setOnClickListener((view) -> {
            finish();
        });
    }


}
