package com.example.uoftlife;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.uoftlife.data.DataFacade;
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
    }

    private void setListeners(){
        findViewById(R.id.button).setOnClickListener((view) ->
                startActivity(new Intent(this, PauseDisplayActivity.class).putExtra("savable",true)));

        findViewById(R.id.button2).setOnClickListener((view) -> {

        });
        findViewById(R.id.button3).setOnClickListener((view) ->
                startActivity(new Intent(this, ConfigActivity.class)));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        GameMessenger.getMessenger().clearAll();
    }
}








