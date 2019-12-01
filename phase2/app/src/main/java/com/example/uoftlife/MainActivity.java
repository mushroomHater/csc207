package com.example.uoftlife;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.uoftlife.data.DataFacade;
import com.example.uoftlife.floating.DifficultySelectActivity;
import com.example.uoftlife.floating.PauseDisplayActivity;
import com.example.uoftlife.gamemap.MapActivity;
import com.example.uoftlife.util.GameMessenger;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeApplication();
        setListeners();
    }

    private void initializeApplication() {
        DataFacade.setContext(getApplicationContext());
        DataFacade.initialize();
        GameMessenger.getMessenger().initialize();
        DataFacade.setTempData("status", String.format(getString(R.string.status_has_due), "username"));
    }

    private void setListeners() {
        findViewById(R.id.start).setOnClickListener((view) ->
                startActivity(new Intent(this, PauseDisplayActivity.class).putExtra("savable", true)));

        findViewById(R.id.load).setOnClickListener((view) -> {
            startActivity(new Intent(this, DifficultySelectActivity.class));
        });
        findViewById(R.id.help).setOnClickListener((view) ->
                startActivity(new Intent(this, MapActivity.class)));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        GameMessenger.getMessenger().clearAll();
    }
}








