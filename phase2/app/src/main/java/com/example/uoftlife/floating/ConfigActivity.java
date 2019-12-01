package com.example.uoftlife.floating;

import android.widget.Button;
import android.widget.TextView;

import com.example.uoftlife.R;
import com.example.uoftlife.util.GameMessenger;

public class ConfigActivity extends FloatingActivity {


    @Override
    protected int setContentLayout() {
        return R.layout.activity_config;
    }

    @Override
    protected String setTitle() {
        return getString(R.string.gameconfig);
    }

    @Override
    protected void initializeView() {
        // todo long click show instruction
        ((TextView) findViewById(R.id.title_message)).setText("New Game");
        ((Button) findViewById(R.id.saveButton)).setText(R.string.randomize);
        ((Button) findViewById(R.id.exitButton)).setText("Go");
        findViewById(R.id.testbtn).setOnClickListener((view) -> {
            GameMessenger.getMessenger().toastMessage("test");
        });
    }

    @Override
    protected void dynamicCreateView() {

    }
}
