package com.example.uoftlife;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class GameLevelThree extends AppCompatActivity {

    private LevelThree game;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_level_three);

        game = new LevelThree(new Score(0, "Fan"));
    }

    void setDoneBtn(){

    }
}
