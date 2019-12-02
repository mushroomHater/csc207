package com.example.uoftlife;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.LayoutRes;
import androidx.appcompat.app.AppCompatActivity;

import com.example.uoftlife.floating.PauseDisplayActivity;
import com.example.uoftlife.util.GameMessenger;

public abstract class GameBaseActivity extends AppCompatActivity {


    private boolean savable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        savable = setSavable();
        setContentView(R.layout.activity_game_base);
        inflateLayout();
        setCommonListeners();
    }

    abstract protected @LayoutRes
    int setContentLayout();

    private void inflateLayout(){
        LayoutInflater inflater = LayoutInflater.from(this);
        inflater.inflate(setContentLayout(), findViewById(R.id.base));
    }

    abstract protected boolean setSavable();

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
}
