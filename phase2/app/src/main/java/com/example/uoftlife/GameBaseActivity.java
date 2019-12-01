package com.example.uoftlife;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.uoftlife.util.GameMessenger;
import com.example.uoftlife.util.calculator.GameProcessorStrategy;

public abstract class GameBaseActivity extends AppCompatActivity {

    private GameProcessorStrategy sceneStrategy;
    private boolean savable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        savable = setSavable();
        sceneStrategy = setSceneStrategy();
        setContentView(R.layout.activity_game_base);
        setCommonListeners();
    }

    abstract protected boolean setSavable();

    abstract protected GameProcessorStrategy setSceneStrategy();

    private void setCommonListeners() {
        findViewById(R.id.pause).setOnClickListener((view) ->
                startActivity(new Intent(this, PauseDisplayActivity.class)
                .putExtra("savable", savable)));
    }


    /**
     * Disable the back operation from navigation bar
     */
    @Override
    public void onBackPressed() {
    }


    /**
     * Avoid clicking any notification outside of app and causing undesirable behavior.
     */
    @Override
    protected void onPause() {
        super.onPause();
        GameMessenger.getMessenger().clearAll();
    }

    private void updateProgress(){
        // todo some calculation and introduce some new structure
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // todo .....
        updateProgress();
        System.out.println("destroyed this base");
    }
}
