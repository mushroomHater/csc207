package com.example.uoftlife;

import android.widget.Button;
import android.widget.TextView;

import com.example.uoftlife.util.GameMessenger;

public class ConfigActivity extends FloatingActivity {


    @Override
    protected int setContentLayout() {
        return R.layout.activity_config;
    }

    @Override
    protected void initializeView() {
        // todo long click show instruction
        ((TextView) findViewById(R.id.title_message)).setText("New Game");
        ((Button) findViewById(R.id.saveButton)).setText(R.string.randomize);
        ((Button) findViewById(R.id.exitButton)).setText("Go");

    }

    @Override
    protected void setListeners() {
        findViewById(R.id.testbtn).setOnClickListener((view)->{
            GameMessenger.getMessenger().toastMessage("test");
        });
    }
}
